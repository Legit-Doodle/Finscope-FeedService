package com.finscope.feed.controllers;

import com.finscope.feed.dto.CommentResponse;
import com.finscope.feed.dto.PostResponse;
import com.finscope.feed.dto.ReactionRequest;
import com.finscope.feed.dto.ReactionResponse;
import com.finscope.feed.entities.Comment;
import com.finscope.feed.entities.Reaction;
import com.finscope.feed.services.FeedService;
import com.finscope.feed.services.ReactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Collections;

@RestController
@RequestMapping("/api/feed")
public class FeedController {
    @Autowired
    private FeedService feedService;

    @Autowired
    private ReactionService reactionService;

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

    // ==================== Reaction Endpoints ====================

    /**
     * Add or update a reaction on a post.
     * If the user already has a reaction, it will be updated.
     * Ensures one reaction per user per post.
     */
    @PutMapping("/{postId}/reactions")
    public Mono<ReactionResponse> addOrUpdateReaction(
            @PathVariable String postId,
            @Valid @RequestBody ReactionRequest request) {
        return reactionService.addOrUpdateReaction(postId, request.getUserId(), request.getReactionType())
                .map(reaction -> new ReactionResponse(Collections.singletonList(reaction), 1))
                .onErrorMap(e -> new RuntimeException("Error adding/updating reaction: " + e.getMessage(), e));
    }

    /**
     * Remove a user's reaction from a post.
     */
    @DeleteMapping("/{postId}/reactions")
    public Mono<ResponseEntity<Void>> removeReaction(
            @PathVariable String postId,
            @RequestParam String userId) {
        return reactionService.removeReaction(postId, userId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .onErrorMap(e -> new RuntimeException("Error removing reaction: " + e.getMessage(), e));
    }

    /**
     * Get the current user's reaction on a specific post.
     * Returns the reaction if exists, or empty response if not.
     */
    @GetMapping("/{postId}/reactions/me")
    public Mono<ReactionResponse> getMyReaction(
            @PathVariable String postId,
            @RequestParam String userId) {
        return reactionService.getMyReaction(postId, userId)
                .map(reaction -> new ReactionResponse(Collections.singletonList(reaction), 1))
                .defaultIfEmpty(ReactionResponse.empty())
                .onErrorMap(e -> new RuntimeException("Error fetching reaction: " + e.getMessage(), e));
    }

    /**
     * Get all reactions for a post.
     */
    @GetMapping("/{postId}/reactions")
    public Mono<ReactionResponse> getReactionsForPost(@PathVariable String postId) {
        return reactionService.getReactionsByPostId(postId)
                .collectList()
                .map(reactions -> new ReactionResponse(reactions, reactions.size()))
                .onErrorMap(e -> new RuntimeException("Error fetching reactions: " + e.getMessage(), e));
    }
}