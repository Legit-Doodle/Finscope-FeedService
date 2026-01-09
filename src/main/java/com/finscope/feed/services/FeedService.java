package com.finscope.feed.services;

import com.finscope.feed.entities.Comment;
import com.finscope.feed.entities.Post;
import com.finscope.feed.repositories.CommentRepository;
import com.finscope.feed.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class FeedService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    public Flux<Post> getFeed() {
        return postRepository.findAll();
    }

    public Flux<Comment> getCommentsForPost(String postId) {
        return commentRepository.findAll()
                .filter(comment -> comment.getPostId().equals(postId));
    }

    public Flux<Comment> createComment(Comment comment) {
        comment.setId(java.util.UUID.randomUUID().toString());
        comment.setCreatedAt(java.time.LocalDateTime.now());
        comment.setUpdatedAt(java.time.LocalDateTime.now());
        comment.setDeleted(false);
        return commentRepository.save(comment).flux();
    }
}
