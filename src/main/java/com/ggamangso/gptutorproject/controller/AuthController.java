package com.ggamangso.gptutorproject.controller;

import org.springframework.stereotype.Controller;
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
}
