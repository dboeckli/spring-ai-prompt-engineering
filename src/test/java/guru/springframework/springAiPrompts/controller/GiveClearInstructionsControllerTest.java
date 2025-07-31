package guru.springframework.springAiPrompts.controller;

import guru.springframework.springAiPrompts.service.GiveClearInstructionsService;
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

import static guru.springframework.springAiPrompts.controller.constants.GiveClearInstructionsConstants.TEXT_COOK_A_STEAK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@Slf4j
class GiveClearInstructionsControllerTest {

    @Mock
    private GiveClearInstructionsService giveClearInstructionsService;

    @InjectMocks
    private GiveClearInstructionsController giveClearInstructionsController;

    @BeforeEach
    void setUp() {
        try (AutoCloseable ignored = openMocks(this)) {
            log.info("Setting up mocks for GiveClearInstructionsControllerTest");
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
        when(giveClearInstructionsService.cookASteak(TEXT_COOK_A_STEAK)).thenReturn(chatResponse);

        ResponseEntity<String> response = giveClearInstructionsController.cookASteak();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedResponse, response.getBody());
        verify(giveClearInstructionsService).cookASteak(TEXT_COOK_A_STEAK);
    }

}