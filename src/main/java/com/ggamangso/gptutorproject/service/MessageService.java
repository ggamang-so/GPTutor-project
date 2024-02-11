package com.ggamangso.gptutorproject.service;

import com.ggamangso.gptutorproject.config.OpenAIConfig;
import com.ggamangso.gptutorproject.constant.MessageType;
import com.ggamangso.gptutorproject.domain.Chat;
import com.ggamangso.gptutorproject.domain.Message;
import com.ggamangso.gptutorproject.domain.dto.MessageDto;
import com.ggamangso.gptutorproject.domain.dto.request.ChatRequest;
import com.ggamangso.gptutorproject.domain.dto.request.MessageRequest;
import com.ggamangso.gptutorproject.repository.ChatRepository;
import com.ggamangso.gptutorproject.repository.MessageRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;

    @Transactional(readOnly = true)
    public List<MessageDto> searchMessages(Long chatId) {
        return messageRepository.findByChat_ChatId(chatId).stream()
                .map(MessageDto::from)
                .toList();
    }


    @Transactional
    public MessageDto saveMessage(MessageDto messageDto) {
        Message message = null;
        try {
            Chat chat = chatRepository.getReferenceById(messageDto.chatDto().chatId());
            message = messageDto.toEntity(chat);


        } catch (EntityNotFoundException e) {
            log.warn("메시지 저장 실패, 메시지 작성에 필요한 정보를 찾을 수 없습니다  -{}", e.getLocalizedMessage());
        }
        assert message != null;
        return MessageDto.from(messageRepository.save(message));
    }

    public ChatRequest SetChatRequest(String quest, long chatId) {
        String modifiedQuest = "";
        if (isFirstMessage(chatId)) {
            modifiedQuest = firstPrompt(quest);
        }
        if (!isFirstMessage(chatId)) {
            modifiedQuest = promptModifying(quest);
        }
        List<MessageRequest> messages = new java.util.ArrayList<>(messageRepository.findByChat_ChatId(chatId)
                .stream().map(MessageRequest::from)
                .toList());
        messages.add(MessageRequest.of(MessageType.USER.getValue(), modifiedQuest));
        return ChatRequest.of(
                OpenAIConfig.MODEL,
                messages,
                OpenAIConfig.MAX_TOKEN,
                OpenAIConfig.TEMPERATURE,
                OpenAIConfig.TOP_P
        );
    }

    public boolean isFirstMessage(long chatId) {
        return !(messageRepository.findByChat_ChatId(chatId).size() > 1);
    }

    public String promptModifying(String quest) {

        return "Target sentence : " + quest ;
    }

    public String firstPrompt(String quest) {
        return "Please follow these rules in future conversations:"+
        "1. The target sentence is the sentence after 'Target Sentence :'"+
        "2. Look at the target sentence and modify it to be more conversational expressions used in English-speaking countries."+
        "3. If the target sentence contains profanity or inappropriate language, remove or edit it."+
        "4. Write the corrected sentence after 'Correcting: '"+
        "5. Write your response to the modified sentence after 'Response: '"+
        "6. Please give your answer in conversational style, no more than 2 sentences."+
                "Target sentence : "+quest;

    }

}
