package com.finscope.feed.repositories;


import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.finscope.feed.entities.Post;

public interface PostRepository extends ReactiveCosmosRepository<Post, String> {
}
