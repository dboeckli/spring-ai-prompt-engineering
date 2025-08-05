package ch.guru.spring.ai.prompt.service;

import ch.guru.spring.ai.prompt.test.config.DeepseekApiKeyExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        Assertions.assertNotNull(response);
        List<String> lines = response.lines()
            .map(String::trim)
            .filter(line -> !line.isEmpty())
            .toList();

        // Count the number of reviews and summaries
        long reviewCount = lines.stream().filter(line -> line.startsWith("Review")).count();
        long summaryCount = lines.stream().filter(line -> line.startsWith("Summary:")).count();

        // Assert that there are 6 reviews and 6 summaries
        assertEquals(6, reviewCount, "There should be 6 reviews");
        assertEquals(6, summaryCount, "There should be 6 summaries");

        // Verify sentiment values
        List<String> validSentiments = List.of("Positive", "Negative", "Mixed (Leaning Negative)", "Mixed (Leaning Positive)");
        lines.stream()
            .filter(line -> line.startsWith("Review"))
            .forEach(line -> {
                String[] parts = line.split(":");
                if (parts.length > 1) {
                    String sentiment = parts[1].trim();
                    Assertions.assertTrue(validSentiments.contains(sentiment),
                        "Sentiment '" + sentiment + "' should be one of " + validSentiments);
                } else {
                    Assertions.fail("Review line does not contain a sentiment: " + line);
                }
            });
    }

    @DisplayName("Testing Emotions")
    @Test
    void testingEmotions() {
        ChatResponse chatResponse = inferenceService.getEmotionsOfReview();
        String response = chatResponse.getResult().getOutput().getText();

        log.info("-----------------------------------------------");
        log.info("response is: \n" + response);
        log.info("-----------------------------------------------");

        Assertions.assertNotNull(response);
        List<String> lines = response.lines()
            .map(String::trim)
            .filter(line -> !line.isEmpty())
            .toList();

        // Count the number of reviews and summaries
        long reviewCount = lines.stream().filter(line -> line.startsWith("Review")).count();
        long summaryCount = lines.stream().filter(line -> line.startsWith("Summary:")).count();

        // Assert that there are 6 reviews and 6 summaries
        assertEquals(6, reviewCount, "There should be 6 reviews");
        assertEquals(6, summaryCount, "There should be 6 summaries");
    }

    @DisplayName("Testing for Anger")
    @Test
    void testingForAnger() {
        String expectedResponse = """
            Review 1: no
            Review 2: no
            Review 3: yes
            Review 4: no
            Review 5: yes
            Review 6: yes
        """;
        expectedResponse = expectedResponse.lines()
            .map(String::trim)
            .filter(line -> !line.isEmpty())
            .collect(Collectors.joining("\n"));

        ChatResponse chatResponse = inferenceService.getAngersOfReview();
        String response = chatResponse.getResult().getOutput().getText();
        Assertions.assertNotNull(response);
        response = response.lines()
            .map(String::trim)
            .filter(line -> !line.isEmpty())
            .collect(Collectors.joining("\n"));

        log.info("-----------------------------------------------");
        log.info("response is: \n" + response);
        log.info("-----------------------------------------------");
        assertEquals(expectedResponse, response);
    }

    @DisplayName("Inferring for Topics")
    @Test
    void inferTopics() {
        ChatResponse chatResponse = inferenceService.inferTopics();
        String response = chatResponse.getResult().getOutput().getText();

        log.info("-----------------------------------------------");
        log.info("response is: \n" + response);
        log.info("-----------------------------------------------");

        Assertions.assertNotNull(response);
        assertEquals("survey, satisfaction, NASA, employees, management", response.trim());
    }
}
