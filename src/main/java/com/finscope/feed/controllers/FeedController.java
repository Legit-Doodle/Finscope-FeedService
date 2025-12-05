package com.finscope.feed.controllers;

import com.finscope.feed.dto.FeedApiResponse;
import com.finscope.feed.entities.Post;
import com.finscope.feed.services.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/feed")
public class FeedController {
    @Autowired
    private FeedService feedService;

    @GetMapping
    public FeedApiResponse getAllPosts() {
        try {
            List<Post> posts = feedService.getFeed().collectList().block();
            long count = posts.size();
            FeedApiResponse response = new FeedApiResponse(posts, count);
            return response;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching feed: " + e.getMessage(), e);
        }
    }
}