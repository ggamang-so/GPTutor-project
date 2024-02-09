package com.ggamangso.gptutorproject.domain.dto.request;

public record MessageRequest(
        String role,
        String content
) {
    public static MessageRequest of(String role, String content) {
        return new MessageRequest(role, content);
    }
}
