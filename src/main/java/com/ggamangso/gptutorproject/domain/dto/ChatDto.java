package com.ggamangso.gptutorproject.domain.dto;

import com.ggamangso.gptutorproject.domain.Chat;
import com.ggamangso.gptutorproject.domain.UserAccount;

import java.time.LocalDateTime;

/**
 * DTO for {@link Chat}
 */
public record ChatDto(
        Long chatId,
        UserAccountDto userAccountDto,
        String firstMessage,
        LocalDateTime created_at,
        LocalDateTime updated_at) {

    public static ChatDto of(Long chatId, UserAccountDto userAccountDtos,String firstMessage, LocalDateTime created_at, LocalDateTime updated_at) {
        return new ChatDto(chatId, userAccountDtos,firstMessage, created_at, updated_at);
    }

    public static ChatDto of(UserAccountDto userAccountDtos, String firstMessage) {
        return new ChatDto(null, userAccountDtos, firstMessage, null, null);
    }


    public static ChatDto from(Chat entity) {
        return new ChatDto(
                entity.getChatId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getFirstMessage(),
                entity.getCreated_at(),
                entity.getUpdated_at()
        );
    }

    public Chat toEntity(UserAccount userAccount) {
        return Chat.of(userAccount, firstMessage);
    }
}
