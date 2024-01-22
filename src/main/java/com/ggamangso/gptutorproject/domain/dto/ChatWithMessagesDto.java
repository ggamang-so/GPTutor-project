package com.ggamangso.gptutorproject.domain.dto;

import com.ggamangso.gptutorproject.domain.Chat;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DTO for {@link Chat}
 */
public record ChatWithMessagesDto(
        Long chatId,
        UserAccountDto userAccountDto,
        Set<MessageDto> messageDtos,
        String firstMessage,
        LocalDateTime created_at,
        LocalDateTime updated_at) {

    public static ChatWithMessagesDto of(Long chatId, UserAccountDto userAccountDtos, Set<MessageDto> messageDtos, String firstMessage, LocalDateTime created_at, LocalDateTime updated_at) {
        return new ChatWithMessagesDto(chatId, userAccountDtos, messageDtos,firstMessage, created_at, updated_at);
    }

    public static ChatWithMessagesDto of(UserAccountDto userAccountDtos, Set<MessageDto> messageDtos , String firstMessage) {
        return new ChatWithMessagesDto(null, userAccountDtos, messageDtos,firstMessage, null, null);
    }


    public static ChatWithMessagesDto from(Chat entity) {
        return new ChatWithMessagesDto(
                entity.getChatId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getMessages().stream()
                        .map(MessageDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)),
                entity.getFirstMessage(),
                entity.getCreated_at(),
                entity.getUpdated_at()
        );
    }
}
