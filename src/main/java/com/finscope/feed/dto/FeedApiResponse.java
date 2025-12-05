package com.finscope.feed.dto;

import com.finscope.feed.entities.Post;

import java.util.List;

public class FeedApiResponse extends ApiResponse<Post> {
    public FeedApiResponse(List<Post> data, long count) {
        super(data, count);
    }
}
