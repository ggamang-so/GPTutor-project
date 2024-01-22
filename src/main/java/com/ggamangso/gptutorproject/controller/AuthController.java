package com.ggamangso.gptutorproject.controller;

import com.ggamangso.gptutorproject.domain.dto.ChatDto;
import com.ggamangso.gptutorproject.domain.dto.MessageDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginView() {
        return "/login";
    }

    @GetMapping("signup")
    public String signupView(){
        return "/signup";
    }

    @GetMapping("mypage")
    public String myPageView(ModelMap map){
        map.addAttribute("Messages", "messageDto");

        return "chats/myPage";
    }

}
