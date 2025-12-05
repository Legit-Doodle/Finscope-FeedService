package com.finscope.feed.entities;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Container(containerName = "post")
public class Post {
    @Id
    private String id;
    private String title;
    private String summary;
    private String image_url;
    private String source_name;
    private String source_url;
    private LocalDateTime published_at;
    private LocalDateTime created_at;
    private boolean is_verified;
    @PartitionKey
    private String partitionKey;
}
