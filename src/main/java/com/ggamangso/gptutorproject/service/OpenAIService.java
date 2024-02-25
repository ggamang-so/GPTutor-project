package com.ggamangso.gptutorproject.service;


import com.ggamangso.gptutorproject.constant.MessageType;
import com.ggamangso.gptutorproject.domain.dto.ChatDto;
import com.ggamangso.gptutorproject.domain.dto.MessageDto;
import com.ggamangso.gptutorproject.domain.dto.request.ChatRequest;
import com.ggamangso.gptutorproject.domain.dto.response.ChatResponse;
import com.ggamangso.gptutorproject.repository.ChatRepository;
import com.ggamangso.gptutorproject.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OpenAIService {
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;

    @Value("${openAI_key}")
    private String apiKey;

    private RestClient restClient;

    public List<MessageDto> correctingContent(ChatRequest request, String quest, long chatId) {
        restClient = RestClient.builder()
                .baseUrl("https://api.openai.com/v1/chat/completions")
                .build();
        ChatResponse response = restClient.post()
                .headers(header -> {
                    header.setBearerAuth(apiKey);
                    header.setContentType(MediaType.APPLICATION_JSON);
                })
                .body(request)
                .retrieve()
                .body(ChatResponse.class);

        assert response != null;
        return seperatingResponse(response, quest, chatId);

    }

    public List<MessageDto> seperatingResponse(ChatResponse chatResponse, String quest, long chatId) {
        log.info(chatResponse.toString());
        String content = chatResponse.choices().get(0).message().content();
        log.info("response content = " + content);
        String[] parts = content.split("Correcting: ");

        // "Response: "를 기준으로 문자열을 분할하여 각 부분을 추출
        String[] responseParts = parts[1].split("Response: ");

        // 각 부분을 각각의 변수에 저장
        String correcting = parts[1].substring(0, parts[1].indexOf("Response: ")).trim();
        String response = responseParts[1].trim();
        log.info(correcting);
        log.info(response);

       ChatDto chatDto = ChatDto.from(chatRepository
                                       .findByChatId(chatId)
                                       .orElseThrow(NullPointerException::new));

        return List.of(MessageDto.of(chatDto, MessageType.USER.getValue(), quest, correcting, false, null),
                MessageDto.of(chatDto, MessageType.ASSISTANT.getValue(), response, null, false, null));
    }


}
