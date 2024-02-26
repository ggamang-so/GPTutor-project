package com.ggamangso.gptutorproject.service;

import com.ggamangso.gptutorproject.constant.AuthorityType;
import com.ggamangso.gptutorproject.constant.MessageType;
import com.ggamangso.gptutorproject.domain.Chat;
import com.ggamangso.gptutorproject.domain.Message;
import com.ggamangso.gptutorproject.domain.UserAccount;
import com.ggamangso.gptutorproject.domain.dto.ChatDto;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 채팅")
@ExtendWith(MockitoExtension.class)
@Transactional
class ChatServiceTest {

    @InjectMocks
    private ChatService sut;

    @Mock
    private ChatRepository chatRepository;
    @Mock
    private MessageRepository messageRepository;
    @Mock
    private UserAccountRepository userAccountRepository;
    @Mock
    private GoogleSTTService googleSTTService;
    @Mock
    private OpenAIService openAIService;

    @DisplayName("유저아이디로 접속시,채팅 목록을 반환한다. ")
    @Test
    void givenUserId_whenSearchingChats_thenReturnChatsList() {
        //Given
        String userId = "ggamangso";
        Chat expectChat1 = createChat(1L, "firstMessage1");
        Chat expectChat2 = createChat(2L, "firstMessage2");
        List<Chat> chats = List.of(expectChat1, expectChat2);
        given(chatRepository.findByUserAccount_UserIdOrderByChatIdDesc(userId)).willReturn(chats);
        //When
        List<ChatDto> actual = sut.searchChats(userId);
        //Then
        assertThat(actual).hasSize(2);
        assertThat(actual)
                .extracting("chatId", "firstMessage")
                .containsExactlyInAnyOrder(
                        tuple(1L, "firstMessage1"),
                        tuple(2L, "firstMessage2")
                );
        then(chatRepository).should().findByUserAccount_UserIdOrderByChatIdDesc(userId);
    }

    @DisplayName("채팅ID를 입력하면, 채팅을 삭제한다. ")
    @Test
    void givenChatId_whenDeletingChat_thenDeletesChat() {
        //Given
        String userId = "ggamangso";
        Long chatId = 1L;
        willDoNothing().given(chatRepository).deleteByChatId(chatId);
        given(chatRepository.getReferenceByChatId(chatId)).willReturn(createChat(chatId));
        //When
        sut.deleteChat(chatId, userId);
        //Then
        then(chatRepository).should().deleteByChatId(chatId);
    }

    @DisplayName("첫 메시지를 입력하면, 채팅이 생성된다.")
    @Test
    void givenFirstMessage_whenSavingChat_thenSavesChat ()
    {
    //Given
        String firstMessage = "firstMessage";
        String userId = "ggamangso";
        ChatDto chatDto = createChatDto(userId, firstMessage);
        given(userAccountRepository.getReferenceById(chatDto.userAccountDto().userId())).willReturn(createUserAccount());
        given(chatRepository.saveAndReturnChatId(any(Chat.class))).willReturn(anyLong());
    //When
        sut.saveChat(chatDto);
    //Then
        then(userAccountRepository).should().getReferenceById(chatDto.userAccountDto().userId());
        then(chatRepository).should().saveAndReturnChatId(any(Chat.class));


    }


    //fixture
    @Value("${openAI_key")
    private String apiKey;
    private Chat createChat() {
        return Chat.of(
                1L,
                createUserAccount(),
                "firstMessage");
    }

    private Chat createChat(Long chatId) {
        return createChat(chatId, "firstMessage");
    }


    private Chat createChat(Long chatId, String firstMessage) {
        Chat chat = Chat.of(
                chatId,
                createUserAccount(),
                firstMessage
        );
        chat.addMessages(Set.of(
                createMessage(1L),
                createMessage(2L)
        ));

        return chat;
    }
    private ChatDto createChatDto(String userId, String firstMessage) {
        return ChatDto.of(
                createUserAccountDto(userId),
                firstMessage
        );
    }

    private UserAccountDto createUserAccountDto(String userId) {
        return UserAccountDto.of(
                userId,
                "test",
                AuthorityType.USER.getValue(),
                "test@email.com",
                "ggamang",
                "memo",
                null,
                null
        );
    }

    private Message createMessage(long id) {
        Message message = Message.of(
                createChat(),
                MessageType.USER,
                "test_content",
                "test_corrected_content",
                false,
                null
        );

        ReflectionTestUtils.setField(message, "id", id);
        return message;
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
