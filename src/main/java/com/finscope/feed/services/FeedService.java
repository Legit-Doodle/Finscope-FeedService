package com.finscope.feed.services;

import com.finscope.feed.entities.Post;
import com.finscope.feed.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class FeedService {
    @Autowired
    private PostRepository postRepository;

    public Flux<Post> getFeed() {
        return postRepository.findAll();
    }
}
