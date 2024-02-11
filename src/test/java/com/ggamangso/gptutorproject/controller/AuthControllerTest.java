package com.ggamangso.gptutorproject.controller;

import com.ggamangso.gptutorproject.config.TestSecurityConfig;
import com.ggamangso.gptutorproject.repository.UserAccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(TestSecurityConfig.class)
@WebMvcTest(AuthController.class)
class AuthControllerTest {

    private final MockMvc mvc;
    public AuthControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @MockBean
    private UserAccountRepository userAccountRepository;

    @DisplayName("[view][Get] 로그인 페이지")
    @Test
    void givenNothing_whenRequestLoginView_thenReturnLoginView () throws Exception {
        //Given

        //When & Then
        mvc.perform(get("/loginForm"))
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

    @DisplayName("[view][Get] 마이페이지 - 인증 없이 접근시")
    @Test
    void givenChatId_when_then () throws Exception {
        //Given
        Long chat_id = 1L;
        //When & Then
        mvc.perform(get("/mypage"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/loginForm"));

    }

    @WithMockUser
    @DisplayName("[view][Get] 마이페이지 - 정상호출, 인증 사용자")
    @Test
    void givenAuthorizedUserAndChatId_when_then () throws Exception {
        //Given
        Long chat_id = 1L;
        //When & Then
        mvc.perform(get("/mypage"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(model().attributeExists("Messages"));

    }
}