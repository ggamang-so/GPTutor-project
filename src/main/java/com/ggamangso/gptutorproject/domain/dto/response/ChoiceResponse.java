package com.ggamangso.gptutorproject.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ChoiceResponse(
        Integer index,
        MessageResponse messageResponse,
        @JsonProperty("finish_reason") String finishReason
) {
    public static ChoiceResponse of(Integer index, MessageResponse messageResponse, @JsonProperty("finish_reason") String finishReason) {
        return new ChoiceResponse(index, messageResponse, finishReason);
    }


}
