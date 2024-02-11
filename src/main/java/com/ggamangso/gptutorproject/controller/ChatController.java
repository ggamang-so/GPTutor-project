package com.ggamangso.gptutorproject.controller;

import com.ggamangso.gptutorproject.constant.MessageType;
import com.ggamangso.gptutorproject.domain.dto.ChatDto;
import com.ggamangso.gptutorproject.domain.dto.MessageDto;
import com.ggamangso.gptutorproject.domain.dto.request.ChatRequest;
import com.ggamangso.gptutorproject.domain.dto.security.TutorPrincipal;
import com.ggamangso.gptutorproject.repository.ChatRepository;
import com.ggamangso.gptutorproject.repository.MessageRepository;
import com.ggamangso.gptutorproject.service.ChatService;
import com.ggamangso.gptutorproject.service.GoogleSTTService;
import com.ggamangso.gptutorproject.service.MessageService;
import com.ggamangso.gptutorproject.service.OpenAIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final ChatService chatService;
    private final GoogleSTTService googleSTTService;
    private final OpenAIService openAIService;
    private final MessageService messageService;


    @GetMapping("/chats")
    public String chats(ModelMap map, @AuthenticationPrincipal TutorPrincipal tutorPrincipal) {
        String userId = tutorPrincipal.getUsername();
        log.info(userId);
        List<ChatDto> chats = chatService.searchChats(userId);
        map.addAttribute("chats", chats);
        return "chats/new";
    }

    @GetMapping("/chats/{chatId}")
    public String chats(@PathVariable Long chatId, ModelMap map,
                        @AuthenticationPrincipal TutorPrincipal tutorPrincipal) throws Exception {
        String userId = tutorPrincipal.getUsername();
        if (!chatService.searchChat(chatId).userAccountDto().userId().equals(userId)) {
            return "redirect:/chats";
        }
        List<ChatDto> chats = chatService.searchChats(userId);
        map.addAttribute("chats", chats);
        List<MessageDto> messages = messageService.searchMessages(chatId);
        map.addAttribute("messages", messages);
        map.addAttribute("chatId", chatId);
        return "chats/new";
    }

    @GetMapping("/chats/new")
    public String newChat(@AuthenticationPrincipal TutorPrincipal tutorPrincipal){
        long chatId = chatService.createChat(tutorPrincipal.getUsername());
        log.info("newChat 생성하기: " + chatId);
        return "redirect:/chats/"+chatId;

    }

    @PostMapping("/api/speaking_record_ok")
    @ResponseBody
    public List<MessageDto> recodingToText(@RequestParam("file") MultipartFile file,
                                           @RequestParam long chatId) throws Exception {
        System.out.println(chatId);
        String quest = googleSTTService.STTConvert(file); // 음성녹음을 Text로 전환
        ChatRequest request = messageService.SetChatRequest(quest, chatId);
        List<MessageDto> messageDtos = openAIService.correctingContent(request, quest, chatId);
        System.out.println(MessageType.of("assistance"));
        return messageDtos.stream()
                .map(messageService::saveMessage)
                .toList();

    }

    @PostMapping("/api/chats/openAI_ok")
    @ResponseBody
    public List<MessageDto> ChatGPT(@RequestParam String quest,
                                    @RequestParam long chatId) {
        ChatRequest request = messageService.SetChatRequest(quest, chatId);
        List<MessageDto> messageDtos = openAIService.correctingContent(request, quest, chatId);
        return messageDtos.stream()
                .map(messageService::saveMessage)
                .toList();

    }


}
