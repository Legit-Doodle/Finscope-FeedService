package com.finscope.feed.services;

import com.finscope.feed.entities.Reaction;
import com.finscope.feed.entities.ReactionType;
import com.finscope.feed.repositories.ReactionRepository;
import com.azure.cosmos.models.PartitionKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class ReactionService {

    @Autowired
    private ReactionRepository reactionRepository;

    /**
     * Add or update a reaction on a post.
     * Uses composite ID (postId_userId) to ensure one reaction per user per post.
     * If reaction exists, updates the type; otherwise creates new.
     */
    public Mono<Reaction> addOrUpdateReaction(String postId, String userId, ReactionType reactionType) {
        String compositeId = Reaction.generateId(postId, userId);
        LocalDateTime now = LocalDateTime.now();

        return reactionRepository.findById(compositeId)
                .flatMap(existingReaction -> {
                    // Update existing reaction
                    existingReaction.setReactionType(reactionType);
                    existingReaction.setUpdatedAt(now);
                    return reactionRepository.save(existingReaction);
                })
                .switchIfEmpty(Mono.defer(() -> {
                    // Create new reaction
                    Reaction newReaction = new Reaction();
                    newReaction.setId(compositeId);
                    newReaction.setPostId(postId);
                    newReaction.setUserId(userId);
                    newReaction.setReactionType(reactionType);
                    newReaction.setCreatedAt(now);
                    newReaction.setUpdatedAt(now);
                    return reactionRepository.save(newReaction);
                }));
    }

    /**
     * Remove a user's reaction from a post.
     * Returns empty Mono if reaction didn't exist.
     */
    public Mono<Void> removeReaction(String postId, String userId) {
        String compositeId = Reaction.generateId(postId, userId);
        return reactionRepository.deleteById(compositeId, new PartitionKey(postId));
    }

    /**
     * Get the current user's reaction on a specific post.
     * Returns empty Mono if no reaction exists.
     */
    public Mono<Reaction> getMyReaction(String postId, String userId) {
        return reactionRepository.findByPostIdAndUserId(postId, userId);
    }

    /**
     * Get all reactions for a post.
     * Useful for displaying reaction list (partition key query - efficient).
     */
    public Flux<Reaction> getReactionsByPostId(String postId) {
        return reactionRepository.findByPostId(postId);
    }
}
