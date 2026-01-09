package com.finscope.feed.controllers;

import com.finscope.feed.dto.CommentResponse;
import com.finscope.feed.dto.PostResponse;
import com.finscope.feed.entities.Comment;
import com.finscope.feed.services.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/feed")
public class FeedController {
    @Autowired
    private FeedService feedService;

    @GetMapping
    public Mono<PostResponse> getAllPosts() {
        return feedService.getFeed()
                .collectList()
                .map(posts -> new PostResponse(posts, posts.size()))
                .onErrorMap(e -> new RuntimeException("Error fetching feed: " + e.getMessage(), e));
    }

    @GetMapping("/{postId}/comments")
    public Mono<CommentResponse> getAllComments(@PathVariable String postId) {
        return feedService.getCommentsForPost(postId)
                .collectList()
                .map(comments -> new CommentResponse(comments, comments.size()))
                .onErrorMap(e -> new RuntimeException("Error fetching comments: " + e.getMessage(), e));
    }

    @PostMapping("/{postId}/comments")
    public Mono<CommentResponse> createComment(@PathVariable String postId, @RequestBody Comment comment) {
        comment.setPostId(postId);
        return feedService.createComment(comment)
                .collectList()
                .map(comments -> new CommentResponse(comments, comments.size()))
                .onErrorMap(e -> new RuntimeException("Error creating comment: " + e.getMessage(), e));
    }
}