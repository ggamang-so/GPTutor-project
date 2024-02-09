package com.ggamangso.gptutorproject.controller;

import com.ggamangso.gptutorproject.config.OpenAIConfig;
import com.ggamangso.gptutorproject.constant.MessageType;
import com.ggamangso.gptutorproject.domain.dto.ChatDto;
import com.ggamangso.gptutorproject.domain.dto.MessageDto;
import com.ggamangso.gptutorproject.domain.dto.request.ChatRequest;
import com.ggamangso.gptutorproject.domain.dto.request.MessageRequest;
import com.ggamangso.gptutorproject.domain.dto.security.TutorPrincipal;
import com.ggamangso.gptutorproject.service.ChatService;
import com.ggamangso.gptutorproject.service.GoogleSTTService;
import com.ggamangso.gptutorproject.service.MessageService;
import com.ggamangso.gptutorproject.service.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ChatController {
    private final ChatService chatService;
    private final GoogleSTTService googleSTTService;
    private final OpenAIService openAIService;
    private final MessageService messageService;


    @GetMapping("/chats")
    public String chats(ModelMap map, @AuthenticationPrincipal TutorPrincipal tutorPrincipal) {
        String userId = tutorPrincipal.getUsername();
        System.out.println(userId);
        List<ChatDto> chats = chatService.searchChats(userId);
        map.addAttribute("chats", chats);
        return "chats/new";
    }

    @GetMapping("/chats/{chatId}")
    public String chats(@PathVariable Long chatId, ModelMap map,
                        @AuthenticationPrincipal TutorPrincipal tutorPrincipal) throws Exception {
        String userId = tutorPrincipal.getUsername();
        if(!chatService.searchChat(chatId).userAccountDto().userId().equals(userId)){
           return "redirect:/chats";
        }
        List<ChatDto> chats = chatService.searchChats(userId);
        map.addAttribute("chats", chats);
        List<MessageDto> messages = messageService.searchMessages(chatId);
        map.addAttribute("messages", messages);
        return "chats/new";
    }

    @PostMapping("/speaking_record_ok")
    @ResponseBody
    public String recodingToText(@RequestParam("file") MultipartFile file) throws Exception {
        return openAIService.correctingContent(ChatRequest.of(
                OpenAIConfig.MODEL,
                List.of(MessageRequest.of(MessageType.SYSTEM.getValue(), "You are a helpful assistant."),
                        MessageRequest.of(MessageType.USER.getValue(), googleSTTService.STTConvert(file))),
                OpenAIConfig.MAX_TOKEN,
                OpenAIConfig.TEMPERATURE,
                OpenAIConfig.TOP_P
        )).choices().get(0).message().content();
    }

    @GetMapping("/chats/test")
    public String test(@RequestParam(required = false) String response, ModelMap map) {
        if (response != null) map.addAttribute("response", response);
        return "/chats/test";
    }

    @PostMapping("/api/chats/openAI_ok")
    @ResponseBody
    public String ChatGPT(@RequestParam String quest, ModelAndView mav) {
        String response = openAIService.correctingContent(ChatRequest.of(
                OpenAIConfig.MODEL,
                List.of(MessageRequest.of(MessageType.SYSTEM.getValue(), "You are a helpful assistant."),
                        MessageRequest.of(MessageType.USER.getValue(), quest)),
                OpenAIConfig.MAX_TOKEN,
                OpenAIConfig.TEMPERATURE,
                OpenAIConfig.TOP_P
        )).choices().get(0).message().content();
        System.out.println(response);
        return response;
    }

    @PostMapping("/chats/post_test")
    public String post_test(@RequestHeader HttpHeaders headers, @RequestBody ChatRequest request){
        System.out.println(request.toString());
        System.out.println(headers.toString());

        return "redirect:/chats/test";
    }
}
