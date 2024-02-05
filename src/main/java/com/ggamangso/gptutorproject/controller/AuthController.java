package com.ggamangso.gptutorproject.controller;

import com.ggamangso.gptutorproject.domain.dto.UserAccountDto;
import com.ggamangso.gptutorproject.service.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class AuthController {

    private final UserAccountService userAccountService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserAccountService userAccountService, PasswordEncoder passwordEncoder) {
        this.userAccountService = userAccountService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/index")
    public String indexView(){
        return "/index";
    }

    @GetMapping("/loginForm")
    public String loginView() {
        return "authorization/login";
    }

    @GetMapping("/signup")
    public String signupView(){
        return "authorization/signup";
    }

    //회원가입 ID 중복 검사
    @PostMapping("/signup/duplicate_check")
    @ResponseBody
    public long duplicate_check(@RequestParam("userId") String userId){
        return userAccountService.searchUser(userId).stream().count();
    }

    @PostMapping("/signup/form")
    public String signupForm(UserAccountDto userAccountDto){

        try {
            userAccountService.saveUser(
                    userAccountDto.userId(),
                    userAccountDto.userPassword(),
                    userAccountDto.authority(),
                    userAccountDto.email(),
                    userAccountDto.nickname(),
                    userAccountDto.memo()
                    );
        }catch(Exception e){
            e.printStackTrace();
        }
        return "redirect:/chats";
    }

    @GetMapping("/mypage")
    public String myPageView(ModelMap map){
        map.addAttribute("Messages", "messageDto");

        return "chats/myPage";
    }

    @GetMapping("/auth/find_password")
    public String findPassword(){

        return "authorization/findPassword";
    }
}
