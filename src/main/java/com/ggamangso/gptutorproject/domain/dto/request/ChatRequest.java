package com.ggamangso.gptutorproject.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ChatRequest(
        String model,
        List<MessageRequest> messages,
        @JsonProperty("max_tokens") Integer maxTokens,
        Double temperature,
        @JsonProperty("top_p") Double topP
) {
    public static ChatRequest of(String model, List<MessageRequest> messages, @JsonProperty("max_tokens") Integer maxTokens, Double temperature, @JsonProperty("top_p") Double topP) {
        return new ChatRequest(model, messages, maxTokens, temperature, topP);
    }
}
