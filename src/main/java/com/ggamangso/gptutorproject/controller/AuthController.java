package com.ggamangso.gptutorproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/index")
    public String indexView(){
        return "/index";
    }
    @GetMapping("/login")
    public String loginView() {
        return "authorization/login";
    }

    @GetMapping("/signup")
    public String signupView(){
        return "authorization/signup";
    }

    @GetMapping("/mypage")
    public String myPageView(ModelMap map){
        map.addAttribute("Messages", "messageDto");

        return "chats/myPage";
    }

}
