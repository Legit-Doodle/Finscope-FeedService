package com.finscope.feed.repositories;


import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.finscope.feed.entities.Comment;

public interface CommentRepository extends ReactiveCosmosRepository<Comment, String> {
}
