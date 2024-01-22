package com.ggamangso.gptutorproject.controller;

import com.ggamangso.gptutorproject.config.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(SecurityConfig.class)
@WebMvcTest(AuthController.class)
class AuthControllerTest {

    private final MockMvc mvc;
    public AuthControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[view][Get] 로그인 페이지")
    @Test
    void givenNothing_whenRequestLoginView_thenReturnLoginView () throws Exception {
        //Given

        //When & Then
        mvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML));

    }

    @DisplayName("[view][Get] 회원가입 페이지")
    @Test
    void givenNothing_whenRequestSignupView_thenReturnSignupView () throws Exception {
        //Given

        //When & Then
        mvc.perform(get("/signup"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML));

    }

    @DisplayName("[view][Get] 마이페이지 ")
    @Test
    void givenChatId_when_then () throws Exception {
        //Given
        Long chat_id = 1L;
        //When & Then
        mvc.perform(get("/mypage"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(model().attributeExists("Messages"));

    }
}