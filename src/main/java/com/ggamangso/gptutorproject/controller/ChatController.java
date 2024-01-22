package com.ggamangso.gptutorproject.controller;

import com.ggamangso.gptutorproject.domain.dto.ChatDto;
import com.ggamangso.gptutorproject.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ChatController {
    private final ChatService chatService;


    @GetMapping("/chats")
    public String chats(ModelMap map){
        String user_id = "ggamangso"; // 추후 @AuthenticationPrincipal 넣어서 교체 예정
        List<ChatDto> chats = chatService.searchChats(user_id);
        map.addAttribute("chats", chats);
        return "chats/new";
    }

//    @PostMapping("/chats/{chatId}")
//    public String chat(ModelMap map){
//        String user_id = "ggamangso"; // 추후 @AuthenticationPrincipal 넣어서 교체 예정
//        List<Message> chats = chatService.searchChats(user_id);
//        map.addAttribute("chats", chats);
//        return "chats/new";
//    }


}
