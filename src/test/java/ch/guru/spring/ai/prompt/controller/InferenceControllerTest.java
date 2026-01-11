package ch.guru.spring.ai.prompt.controller;

import ch.guru.spring.ai.prompt.service.InferenceService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@Slf4j
class InferenceControllerTest {

    @Mock
    private InferenceService inferenceService;

    @InjectMocks
    private InferenceController InferenceController;

    @BeforeEach
    void setUp() {
        try (AutoCloseable ignored = openMocks(this)) {
            log.info("Setting up mocks for GiveClearInstructionsControllerTest");
        } catch (Exception e) {
            throw new RuntimeException("Failed to open mocks", e);
        }
    }

    @Test
    void getSentiments() {
        String expectedResponse = "AI is working";
        AssistantMessage expectedAssistantMessage = new AssistantMessage(expectedResponse);
        // Create a valid ChatResponse
        ChatResponse chatResponse = new ChatResponse(List.of(new Generation(expectedAssistantMessage)));
        when(inferenceService.getSentimentsOfReview()).thenReturn(chatResponse);

        ResponseEntity<String> response = InferenceController.getSentiments();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedResponse, response.getBody());
        verify(inferenceService).getSentimentsOfReview();
    }

    @Test
    void getEmotions() {
        String expectedResponse = "AI is working";
        AssistantMessage expectedAssistantMessage = new AssistantMessage(expectedResponse);
        // Create a valid ChatResponse
        ChatResponse chatResponse = new ChatResponse(List.of(new Generation(expectedAssistantMessage)));
        when(inferenceService.getEmotionsOfReview()).thenReturn(chatResponse);

        ResponseEntity<String> response = InferenceController.getEmotions();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedResponse, response.getBody());
        verify(inferenceService).getEmotionsOfReview();
    }

    @Test
    void getAngers() {
        String expectedResponse = "AI is working";
        AssistantMessage expectedAssistantMessage = new AssistantMessage(expectedResponse);
        // Create a valid ChatResponse
        ChatResponse chatResponse = new ChatResponse(List.of(new Generation(expectedAssistantMessage)));
        when(inferenceService.getAngersOfReview()).thenReturn(chatResponse);

        ResponseEntity<String> response = InferenceController.getAngers();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedResponse, response.getBody());
        verify(inferenceService).getAngersOfReview();
    }

    @Test
    void getInferTopics() {
        String expectedResponse = "AI is working";
        AssistantMessage expectedAssistantMessage = new AssistantMessage(expectedResponse);
        // Create a valid ChatResponse
        ChatResponse chatResponse = new ChatResponse(List.of(new Generation(expectedAssistantMessage)));
        when(inferenceService.inferTopics()).thenReturn(chatResponse);

        ResponseEntity<String> response = InferenceController.getInferTopics();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedResponse, response.getBody());
        verify(inferenceService).inferTopics();
    }
}