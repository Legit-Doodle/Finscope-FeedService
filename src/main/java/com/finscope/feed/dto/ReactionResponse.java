package com.finscope.feed.dto;

import com.finscope.feed.entities.Reaction;

import java.util.Collections;
import java.util.List;

public class ReactionResponse extends ApiResponse<Reaction> {

    public ReactionResponse(List<Reaction> data, long count) {
        super(data, count);
    }

    public static ReactionResponse empty() {
        return new ReactionResponse(Collections.emptyList(), 0);
    }
}
