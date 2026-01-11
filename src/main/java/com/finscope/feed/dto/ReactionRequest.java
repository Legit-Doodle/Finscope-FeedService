package com.finscope.feed.dto;

import com.finscope.feed.entities.ReactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReactionRequest {

    @NotBlank(message = "userId is required")
    private String userId;

    @NotNull(message = "reactionType is required")
    private ReactionType reactionType;
}
