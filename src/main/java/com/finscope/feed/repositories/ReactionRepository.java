package com.finscope.feed.repositories;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.finscope.feed.entities.Reaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactionRepository extends ReactiveCosmosRepository<Reaction, String> {

    /**
     * Find all reactions for a specific post.
     * Efficient query as postId is the partition key.
     */
    Flux<Reaction> findByPostId(String postId);

    /**
     * Find a specific user's reaction on a post.
     * Uses partition key (postId) for efficient lookup.
     */
    Mono<Reaction> findByPostIdAndUserId(String postId, String userId);
}
