package com.ggamangso.gptutorproject.domain.dto.request;

import com.ggamangso.gptutorproject.constant.MessageType;

public record MessageRequest(
        MessageType role,
        String content
) {
    public static MessageRequest of(MessageType role, String content) {
        return new MessageRequest(role, content);
    }
}
