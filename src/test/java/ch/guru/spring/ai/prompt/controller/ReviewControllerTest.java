package ch.guru.spring.ai.prompt.controller;

import ch.guru.spring.ai.prompt.service.ReviewService;
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

import static ch.guru.spring.ai.prompt.controller.constants.ReviewConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@Slf4j
class ReviewControllerTest {

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private ReviewController reviewController;

    @BeforeEach
    void setUp() {
        try (AutoCloseable ignored = openMocks(this)) {
            log.info("Setting up mocks for QuestionControllerTest");
        } catch (Exception e) {
            throw new RuntimeException("Failed to open mocks", e);
        }
    }

    @Test
    void createReview_Success() {
        String expectedResponse = "AI is working";
        AssistantMessage expectedAssistantMessage = new AssistantMessage(expectedResponse);
        // Create a valid ChatResponse
        ChatResponse chatResponse = new ChatResponse(List.of(new Generation(expectedAssistantMessage)));
        when(reviewService.createReview(REVIEW_PROMPT, REVIEW_1)).thenReturn(chatResponse);

        ResponseEntity<String> response = reviewController.createReview();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedResponse, response.getBody());
        verify(reviewService).createReview(REVIEW_PROMPT, REVIEW_1);
    }

    @Test
    void createReviews() {
        String expectedResponse = "AI is working";
        AssistantMessage expectedAssistantMessage = new AssistantMessage(expectedResponse);
        // Create a valid ChatResponse
        ChatResponse chatResponse = new ChatResponse(List.of(new Generation(expectedAssistantMessage)));
        when(reviewService.createReview(REVIEW_PROMPT_3, REVIEW_1, REVIEW_2, REVIEW_3)).thenReturn(chatResponse);

        ResponseEntity<String> response = reviewController.createReviews();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedResponse, response.getBody());
        verify(reviewService).createReview(REVIEW_PROMPT_3, REVIEW_1, REVIEW_2, REVIEW_3);
    }

    @Test
    void createDescriptionFromReviewsExtract() {
        String expectedResponse = "AI is working";
        AssistantMessage expectedAssistantMessage = new AssistantMessage(expectedResponse);
        // Create a valid ChatResponse
        ChatResponse chatResponse = new ChatResponse(List.of(new Generation(expectedAssistantMessage)));
        when(reviewService.createReview(REVIEW_PROMPT_4, REVIEW_1, REVIEW_2, REVIEW_3)).thenReturn(chatResponse);

        ResponseEntity<String> response = reviewController.createDescriptionFromReviewsExtract();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedResponse, response.getBody());
        verify(reviewService).createReview(REVIEW_PROMPT_4, REVIEW_1, REVIEW_2, REVIEW_3);
    }
}