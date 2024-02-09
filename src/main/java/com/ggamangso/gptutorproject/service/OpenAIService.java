package com.ggamangso.gptutorproject.service;


import com.ggamangso.gptutorproject.domain.dto.request.ChatRequest;
import com.ggamangso.gptutorproject.domain.dto.response.ChatResponse;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
@Service
public class OpenAIService {

    @Value("${openAI_key}")
    private String apiKey;

    private RestClient restClient;

    @PostConstruct
    public void init() {
        System.out.println("init에서 조회 : "+apiKey);


    }


    public ChatResponse correctingContent(ChatRequest request) {
        restClient = RestClient.builder()
                .baseUrl("https://api.openai.com/v1/chat/completions")
                .build();
        System.out.println("correctingContent에서 조회 : "+apiKey);
        return restClient.post()
                .headers(header->{
                    header.setBearerAuth(apiKey);
                    header.setContentType(MediaType.APPLICATION_JSON);
                })
                .body(request)
                .retrieve()
                .body(ChatResponse.class);

    }

}
