package com.ggamangso.gptutorproject.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UsageResponse(
        @JsonProperty("total_tokens") Integer totalTokens,
        @JsonProperty("prompt_tokens") Integer promptTokens ,
        @JsonProperty("completion_tokens") Integer completionTokens
) {
}
