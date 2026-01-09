package com.finscope.feed.dto;

import com.finscope.feed.entities.Post;

import java.util.List;

public class PostResponse extends ApiResponse<Post> {
    public PostResponse(List<Post> data, long count) {
        super(data, count);
    }
}
