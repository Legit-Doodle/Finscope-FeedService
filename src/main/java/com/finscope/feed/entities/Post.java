package com.finscope.feed.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private Long id;
    private String title;
    private String mediaUrl;
    private List<String> tags;
    private String content;
    private Long postedBy;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;
}
