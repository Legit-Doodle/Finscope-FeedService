package com.finscope.feed.controllers;

import com.finscope.feed.dto.FeedApiResponse;
import com.finscope.feed.services.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/feed")
public class FeedController {
    @Autowired
    private FeedService feedService;

    @GetMapping
    public Mono<FeedApiResponse> getAllPosts() {
        return feedService.getFeed()
                .collectList()
                .map(posts -> new FeedApiResponse(posts, posts.size()))
                .onErrorMap(e -> new RuntimeException("Error fetching feed: " + e.getMessage(), e));
    }
}