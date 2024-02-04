package com.ggamangso.gptutorproject.domain.dto.response;

public record MessageResponse(
        String role,
        String content
) {

    public static MessageResponse of(String role, String content) {
        return new MessageResponse(role, content);
    }

}
