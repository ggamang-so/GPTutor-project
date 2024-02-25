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
import java.util.Optional;

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
        Optional<Chat> chat = chatRepository.findByChatId(chatId);
        if(chat.isPresent()) {
            return ChatDto.from(chat.get());
        }else{
            throw new NullPointerException("Not Found chat");
        }

    }


    public void deleteChat(Long chatId, String userId) {
        String userIdOfChat = chatRepository.getReferenceByChatId(chatId).getUserAccount().getUserId();
        if(userId.equals(userIdOfChat)){
            chatRepository.deleteByChatId(chatId);
        }

    }

    public void deleteChats(List<Long> chatIds, String userId) {
        for(long chatId : chatIds){
            deleteChat(chatId, userId);
        }
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


    public long searchChatFromMessageId(long messageId) {

        return  messageRepository.findById(messageId)
                .get().getChat().getChatId();
    }
}
