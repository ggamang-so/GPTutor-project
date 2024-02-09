package com.ggamangso.gptutorproject.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ChatResponse(
        String id,
        String object,
        Long created,
        String model,
        List<ChoiceResponse> choices,
        @JsonProperty("system_fingerprint") String systemFingerprint,
        UsageResponse usage
) {
    public static ChatResponse of(String id, String object, Long created, String model, List<ChoiceResponse> choices, @JsonProperty("system_fingerprint") String systemFingerprint, UsageResponse usage) {
        return new ChatResponse(id, object, created, model, choices, systemFingerprint, usage);
    }


}
