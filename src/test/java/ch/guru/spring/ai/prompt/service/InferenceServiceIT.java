package ch.guru.spring.ai.prompt.service;

import ch.guru.spring.ai.prompt.test.config.DeepseekApiKeyExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
@ExtendWith(DeepseekApiKeyExtension.class)
@Slf4j
public class InferenceServiceIT {

    @Autowired
    InferenceService inferenceService;

    @DisplayName("Testing Sentiments")
    @Test
    void testingSentiments() {
        ChatResponse chatResponse = inferenceService.getSentimentsOfReview();
        String response = chatResponse.getResult().getOutput().getText();

        log.info("-----------------------------------------------");
        log.info("response is: \n" + response);
        log.info("-----------------------------------------------");
    }

    @DisplayName("Testing Emotions")
    @Test
    void testingEmotions() {
        ChatResponse chatResponse = inferenceService.getEmotionsOfReview();
        String response = chatResponse.getResult().getOutput().getText();

        log.info("-----------------------------------------------");
        log.info("response is: \n" + response);
        log.info("-----------------------------------------------");
    }

    @DisplayName("Testing for Anger")
    @Test
    void testingForAnger() {
        ChatResponse chatResponse = inferenceService.getAngersOfReview();
        String response = chatResponse.getResult().getOutput().getText();

        log.info("-----------------------------------------------");
        log.info("response is: \n" + response);
        log.info("-----------------------------------------------");
    }

    @DisplayName("Inferring for Topics")
    @Test
    void inferTopics() {
        ChatResponse chatResponse = inferenceService.inferTopics();
        String response = chatResponse.getResult().getOutput().getText();

        log.info("-----------------------------------------------");
        log.info("response is: \n" + response);
        log.info("-----------------------------------------------");
    }
}
