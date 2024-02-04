package com.ggamangso.gptutorproject.controller;

import com.ggamangso.gptutorproject.config.TestSecurityConfig;
import com.ggamangso.gptutorproject.repository.ChatRepository;
import com.ggamangso.gptutorproject.repository.UserAccountRepository;
import com.ggamangso.gptutorproject.service.ChatService;
import com.ggamangso.gptutorproject.service.GoogleSTTService;
import com.ggamangso.gptutorproject.service.MessageService;
import com.ggamangso.gptutorproject.service.OpenAIService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("[Controller] - chat")
@Import({TestSecurityConfig.class})
@WebMvcTest(ChatController.class)
class ChatControllerTest {

    private final MockMvc mvc;
    @MockBean
    private final ChatService chatService;
    @MockBean
    private final GoogleSTTService googleSTTService;
    @MockBean
    private final OpenAIService openAIService;
    @MockBean
    private final MessageService messageService;

    public ChatControllerTest(@Autowired MockMvc mvc,@Autowired ChatService chatService,@Autowired GoogleSTTService googleSTTService,@Autowired OpenAIService openAIService,@Autowired MessageService messageService) {
        this.mvc = mvc;
        this.chatService = chatService;
        this.googleSTTService = googleSTTService;
        this.openAIService = openAIService;
        this.messageService = messageService;
    }

    @WithUserDetails(value = "unoTest", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @DisplayName("[view][Post] 채팅 시작 페이지  - 정상호출")
    @Test
    void givenNothing_whenRequestChatsView_thenReturnChatsView () throws Exception {
    //Given

    //When & Then
        mvc.perform(get("/chats"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(model().attributeExists("chats"));
    }

//    @DisplayName("[view][Get] 특정 채팅 페이지 - chat_id와 함께 호출")
//    @Test
//    void givenChatId_whenRequestChatView_thenReturnChatView () throws Exception {
//    //Given
//        Long chat_id = 1L;
//    //When & Then
//        mvc.perform(get("/chats/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
//                .andExpect(model().attributeExists("chatWithMessages"));
//
//    }



}