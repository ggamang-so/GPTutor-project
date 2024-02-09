package com.ggamangso.gptutorproject.service;

import com.ggamangso.gptutorproject.constant.AuthorityType;
import com.ggamangso.gptutorproject.constant.MessageType;
import com.ggamangso.gptutorproject.domain.Chat;
import com.ggamangso.gptutorproject.domain.Message;
import com.ggamangso.gptutorproject.domain.UserAccount;
import com.ggamangso.gptutorproject.domain.dto.ChatDto;
import com.ggamangso.gptutorproject.domain.dto.MessageDto;
import com.ggamangso.gptutorproject.domain.dto.UserAccountDto;
import com.ggamangso.gptutorproject.repository.ChatRepository;
import com.ggamangso.gptutorproject.repository.MessageRepository;
import com.ggamangso.gptutorproject.repository.UserAccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 메시지")
@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @InjectMocks private MessageService sut;

    @Mock private MessageRepository messageRepository;
    @Mock private UserAccountRepository userAccountRepository;
    @Mock private ChatRepository chatRepository;

    @DisplayName("채팅ID로 조회하면 message List를 반환한다. ")
    @Test
    void givenChatId_whenSearchingMessage_thenReturnMessagesList () {
        //Given
        Long chatId = 1L;
        Message expectMessage1 = createMessage(1L, chatId, "firstMessage1" );
        Message expectMessage2 = createMessage(2L, chatId, "firstMessage2" );
        List<Message> messages = List.of(expectMessage1, expectMessage2);
        given(messageRepository.findByChat_ChatId(chatId)).willReturn(messages);
        //When
        List<MessageDto> actual = sut.searchMessages(chatId);
        //Then
        assertThat(actual).asList().hasSize(2);
        assertThat(actual).asList()
                .extracting("id")
                .containsExactlyInAnyOrder(1L, 2L);
        then(messageRepository).should().findByChat_ChatId(chatId);
    }

    @DisplayName("사용자가 message를 입력하면, message를 저장한다. ")
    @Test
    void givenMessageContent_whenSavingMessage_thenSavesMessage () {
        //Given
        MessageDto messageDto = createMessageDto("message_content");
        given(chatRepository.getReferenceById(messageDto.chatDto().chatId())).willReturn(createChat());
        given(messageRepository.save(any(Message.class))).willReturn(null);

        //When
        sut.saveMessages(messageDto);
        //Then
        then(chatRepository).should().getReferenceById(messageDto.chatDto().chatId());
        then(messageRepository).should().save(any(Message.class));
    }

    @DisplayName("Google STT API로 답변을 받으면, 답변 message를 저장한다. ")
    @Test
    void givenAPIResponse_whenSavingResponseMessage_thenSavesMessage () {
        //Given
        MessageDto messageDto = createMessageDto("message_content",MessageType.ASSISTANT.getValue());
        given(chatRepository.getReferenceById(messageDto.chatDto().chatId())).willReturn(createChat());
        given(messageRepository.save(any(Message.class))).willReturn(null);

        //When
        sut.saveMessages(messageDto);
        //Then
        then(chatRepository).should().getReferenceById(messageDto.chatDto().chatId());
        then(messageRepository).should().save(any(Message.class));
    }

    @DisplayName("Google STT API로부터 받은 답변을 ChatGPT API로 보내서 답변을 받으면 , message correct_content 컬럼을 업데이트한다. ")
    @Test
    void givenAPIResponse_whenSavingResponseMessage_thenSavesCorrectContentInMessage () {
        //Given
        MessageDto messageDto = createMessageDto("message_content",MessageType.ASSISTANT.getValue());
        given(chatRepository.getReferenceById(messageDto.chatDto().chatId())).willReturn(createChat());
        given(messageRepository.save(any(Message.class))).willReturn(null);

        //When
        sut.saveMessages(messageDto);
        //Then
        then(chatRepository).should().getReferenceById(messageDto.chatDto().chatId());
        then(messageRepository).should().save(any(Message.class));
    }






    //fixture
    private Chat createChat() {
        return Chat.of(
            1L,
                createUserAccount(),
                "firstMessage"
        );
    }

    private MessageDto createMessageDto(String messageContent) {
        return createMessageDto(messageContent, MessageType.USER.getValue());
    }
    private MessageDto createMessageDto(String messageContent, String role) {
        return MessageDto.of(
                1L,
                createChatDto(),
                role,
                messageContent,
                messageContent+"correct",
                false,
                null,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    private ChatDto createChatDto() {
        return ChatDto.of(
                1L,
                createUserAccountDto(),
                "firstMessage",
                LocalDateTime.now(),
                LocalDateTime.now()


        );
    }

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(
                "ggamangso",
                "test1234",
                AuthorityType.USER.getValue(),
                "test@mail.com",
                "ggamang",
                null,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    private Message createMessage(long id, Long chatId, String firstMessage) {
        Message message = Message.of(
                createChat(chatId, firstMessage),
                MessageType.USER,
                "test_content",
                "test_corrected_content",
                false,
                null
        );

        ReflectionTestUtils.setField(message, "id", id);
        return message;
    }
    private Chat createChat(Long chatId, String firstMessage ){
        return Chat.of(chatId, createUserAccount(), firstMessage);
    }

    private UserAccount createUserAccount() {
        return UserAccount.of(
                "ggamangso",
                "test1234",
                AuthorityType.USER,
                "test@mail.com",
                "ggamang",
                null
        );
    }

}