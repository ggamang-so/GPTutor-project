package com.ggamangso.gptutorproject.service;

import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class GoogleSTTService {

    public String STTConvert(MultipartFile file){ // 입력된 multipartFile을 google STT api로 보내 Text로 변환하여 받음
        StringBuilder convertedText = new StringBuilder();
        try(SpeechClient speech = SpeechClient.create()) {
            ByteString audioBytes = ByteString.copyFrom(file.getBytes());

            // Configure request with local raw PCM audio
            RecognitionConfig config =
                    RecognitionConfig.newBuilder()
                            .setEncoding(RecognitionConfig.AudioEncoding.WEBM_OPUS)
                            .setLanguageCode("en-US")
                            .setSampleRateHertz(48000)
                            .build();
            RecognitionAudio audio = RecognitionAudio.newBuilder().setContent(audioBytes).build();

            // Use blocking call to get audio transcript
            RecognizeResponse response = speech.recognize(config, audio);
            List<SpeechRecognitionResult> results = response.getResultsList();

            for (SpeechRecognitionResult result : results) {
                // There can be several alternative transcripts for a given chunk of speech. Just use the
                // first (most likely) one here.
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);

                convertedText.append(alternative.getTranscript());
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return convertedText.toString();
    }
}
