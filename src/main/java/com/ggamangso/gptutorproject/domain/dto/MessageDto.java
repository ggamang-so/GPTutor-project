package com.ggamangso.gptutorproject.domain.dto;

import com.ggamangso.gptutorproject.constant.MessageType;
import com.ggamangso.gptutorproject.domain.Chat;
import com.ggamangso.gptutorproject.domain.Message;

import java.time.LocalDateTime;

/**
 * DTO for {@link Message}
 */
public record MessageDto(
        Long id,
        ChatDto chatDto,
        String role,
        String content,
        String correctedContent,
        Boolean isBookmarked,
        String messageMemo,
        LocalDateTime created_at,
        LocalDateTime updated_at
){
    public static MessageDto of(ChatDto chatDto, String role, String content,String correctedContent,
                                Boolean isBookmarked, String messageMemo){
        return new MessageDto(null,chatDto, role, content, correctedContent, isBookmarked, messageMemo, null, null);
    }

    public static MessageDto of(Long id, ChatDto chatDto, String role, String content, String correctedContent, Boolean isBookmarked, String messageMemo, LocalDateTime created_at, LocalDateTime updated_at) {
        return new MessageDto(id, chatDto, role, content, correctedContent, isBookmarked, messageMemo, created_at, updated_at);
    }

    public static MessageDto from(Message entity){
        return new MessageDto(
                entity.getId(),
                ChatDto.from(entity.getChat()),
                entity.getRole().getValue(),
                entity.getContent(),
                entity.getCorrectedContent(),
                entity.getIsBookmarked(),
                entity.getMessageMemo(),
                entity.getCreated_at(),
                entity.getUpdated_at()
        );
    }

    public Message toEntity(Chat chat){
        return Message.of(chat, MessageType.valueOf(role), content, correctedContent, isBookmarked, messageMemo);
    }

}