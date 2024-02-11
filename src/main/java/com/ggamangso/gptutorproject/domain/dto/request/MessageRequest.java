package com.ggamangso.gptutorproject.domain.dto.request;

import com.ggamangso.gptutorproject.domain.Message;

public record MessageRequest(
        String role,
        String content
) {
    public static MessageRequest of(String role, String content) {
        return new MessageRequest(role, content);
    }
    public static MessageRequest from(Message message){
        return MessageRequest.of(message.getRole().getValue(), message.getContent());
    }
}
