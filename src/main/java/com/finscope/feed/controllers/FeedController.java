package com.finscope.feed.controllers;

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
    public List<Post> getAllPosts() {
        try {
            return feedService.getFeed();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching feed: " + e.getMessage(), e);
        }
    }
}
