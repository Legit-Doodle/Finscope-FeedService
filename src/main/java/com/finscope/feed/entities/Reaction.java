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
@Container(containerName = "post_reactions")
public class Reaction {

    @Id
    private String id;

    @PartitionKey
    private String postId;

    private String userId;

    private ReactionType reactionType;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    /**
     * Generates the composite ID for uniqueness constraint.
     * Format: postId_userId
     */
    public static String generateId(String postId, String userId) {
        return postId + "_" + userId;
    }
}
