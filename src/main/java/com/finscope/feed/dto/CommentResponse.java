package com.finscope.feed.dto;

import com.finscope.feed.entities.Comment;
import java.util.List;

public class CommentResponse extends ApiResponse<Comment> {
    public CommentResponse(List<Comment> data, long count) {
        super(data, count);
    }
}
