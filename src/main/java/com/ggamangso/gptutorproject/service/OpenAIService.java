package com.ggamangso.gptutorproject.service;


import com.ggamangso.gptutorproject.domain.dto.request.ChatRequest;
import com.ggamangso.gptutorproject.domain.dto.response.ChatResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@Slf4j
@RequiredArgsConstructor
@Service
public class OpenAIService {

    @Value("${openAI_key}")
    private String apiKey;

    private WebClient client;

    @PostConstruct
    public void init() {
        System.out.println("init에서 조회 : "+apiKey);
        client = WebClient.builder()
//                .baseUrl("http://localhost:9090/chats/post_test")
                .baseUrl("https://api.openai.com/v1/chat/completions")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
    }


    public ChatResponse correctingContent(ChatRequest request) {
        init();
        return client.post()
                .body(Mono.just(request), ChatRequest.class)
                .retrieve()
                .bodyToMono(ChatResponse.class)
                .block();
    }

}
