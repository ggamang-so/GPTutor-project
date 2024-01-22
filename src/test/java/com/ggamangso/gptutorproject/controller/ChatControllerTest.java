package com.ggamangso.gptutorproject.controller;

import com.ggamangso.gptutorproject.config.SecurityConfig;
import com.ggamangso.gptutorproject.service.ChatService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("[Controller] - chat")
@Import(SecurityConfig.class)
@WebMvcTest(ChatController.class)
class ChatControllerTest {

    private final MockMvc mvc;

    @MockBean private ChatService chatService;

    public ChatControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }


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