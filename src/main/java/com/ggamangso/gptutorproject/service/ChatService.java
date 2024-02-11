package com.ggamangso.gptutorproject.service;

import com.ggamangso.gptutorproject.config.OpenAIConfig;
import com.ggamangso.gptutorproject.constant.MessageType;
import com.ggamangso.gptutorproject.domain.Chat;
import com.ggamangso.gptutorproject.domain.Message;
import com.ggamangso.gptutorproject.domain.UserAccount;
import com.ggamangso.gptutorproject.domain.dto.ChatDto;
import com.ggamangso.gptutorproject.repository.ChatRepository;
import com.ggamangso.gptutorproject.repository.MessageRepository;
import com.ggamangso.gptutorproject.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ChatService {
    private final MessageRepository messageRepository;
    private final UserAccountRepository userAccountRepository;
    private final ChatRepository chatRepository;

    @Transactional(readOnly = true)
    public List<ChatDto> searchChats(String userId) {

        return chatRepository.findByUserAccount_UserIdOrderByChatIdDesc(userId)
                .stream()
                .map(ChatDto::from)
                .toList();
    }

    @Transactional
    public ChatDto searchChat(Long chatId) {
        return ChatDto.from(chatRepository.findByChatId(chatId));
    }


    public void deleteChat(Long chatId, String userId) {
        Chat chat = chatRepository.getReferenceByChatId(chatId);
        chatRepository.deleteByChatIdAndUserAccount_UserId(chatId, userId);
        chatRepository.flush();

    }

    public long saveChat(ChatDto chatDto) {
        UserAccount userAccount = userAccountRepository.getReferenceById(chatDto.userAccountDto().userId());
        Chat chat = chatDto.toEntity(userAccount);
        System.out.println(chat.getChatId());
        long chatId = chatRepository.saveAndReturnChatId(chat);
        System.out.println(chat.getChatId());
        return chatId;

    }

    @Transactional
    public long createChat(String userId) {
        UserAccount userAccount = userAccountRepository.getReferenceById(userId);
        long chatId = chatRepository.save(Chat.of(userAccount, null)).getChatId();
        Chat chat = chatRepository.getReferenceByChatId(chatId);
        messageRepository.save(
                Message.of(chat,
                        MessageType.SYSTEM,
                        OpenAIConfig.SYSTEM_MESSAGE,
                        null,
                        false,
                        null
                        ));
        return chatId;
    }


}
