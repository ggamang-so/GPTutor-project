package com.ggamangso.gptutorproject.service;

import com.ggamangso.gptutorproject.domain.Chat;
import com.ggamangso.gptutorproject.domain.Message;
import com.ggamangso.gptutorproject.domain.dto.MessageDto;
import com.ggamangso.gptutorproject.domain.dto.response.MessageResponse;
import com.ggamangso.gptutorproject.repository.ChatRepository;
import com.ggamangso.gptutorproject.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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
        return messageRepository.findByChat_ChatId(chatId)
                .stream().map(MessageDto::from)
                .toList();
    }

    @Transactional
    public void saveMessages(MessageDto messageDto) {
        try{
            Chat chat = chatRepository.getReferenceById(messageDto.chatDto().chatId());
            Message message = messageDto.toEntity(chat);
            messageRepository.save(message);

        }catch(EntityNotFoundException e){
            log.warn("메시지 저장 실패, 메시지 작성에 필요한 정보를 찾을 수 없습니다  -{}", e.getLocalizedMessage());
        }
    }

    public void deleteMessages(Long id) {
        messageRepository.deleteById(id);

    }



    public MessageResponse CorrectingContent(String content){

        return null;
    }
}
