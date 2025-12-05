package com.finscope.feed.dto;

import java.util.List;

public class ApiResponse<T> {
    public List<T> data;
    public long count;

    public ApiResponse(List<T> data, long count) {
        this.data = data;
        this.count = count;
    }
}
