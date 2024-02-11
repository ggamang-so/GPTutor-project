package com.ggamangso.gptutorproject.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ChoiceResponse(
        Integer index,
        MessageResponse message,
        @JsonProperty("finish_reason") String finishReason,
        Object logprobs
) {
    public static ChoiceResponse of(Integer index, MessageResponse message, @JsonProperty("finish_reason") String finishReason, Object logprobs) {
        return new ChoiceResponse(index, message, finishReason, null);
    }


}
