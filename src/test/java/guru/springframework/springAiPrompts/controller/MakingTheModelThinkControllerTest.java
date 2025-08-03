package guru.springframework.springAiPrompts.controller;

import guru.springframework.springAiPrompts.service.MakingTheModelThinkService;
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
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
class MakingTheModelThinkControllerTest {

    @Mock
    private MakingTheModelThinkService makingTheModelThinkService;

    @InjectMocks
    private MakingTheModelThinkController makingTheModelThinkController;

    @BeforeEach
    void setUp() {
        try (AutoCloseable ignored = openMocks(this)) {
            log.info("Setting up mocks for MakingTheModelThinkControllerTest");
        } catch (Exception e) {
            throw new RuntimeException("Failed to open mocks", e);
        }
    }

    @Test
    void test_enigmaWithBall() {
        String expectedResponse = "AI is working";
        AssistantMessage expectedAssistantMessage = new AssistantMessage(expectedResponse);
        // Create a valid ChatResponse
        ChatResponse chatResponse = new ChatResponse(List.of(new Generation(expectedAssistantMessage)));
        when(makingTheModelThinkService.enigmaWithBall()).thenReturn(chatResponse);

        ResponseEntity<String> response = makingTheModelThinkController.enigmaWithBall();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedResponse, response.getBody());
        verify(makingTheModelThinkService).enigmaWithBall();
    }

    @Test
    void test_correct_checkStudentSolution() {
        String expectedResponse = "AI is working";
        AssistantMessage expectedAssistantMessage = new AssistantMessage(expectedResponse);

        // Create a valid ChatResponse
        ChatResponse chatResponse = new ChatResponse(List.of(new Generation(expectedAssistantMessage)));
        when(makingTheModelThinkService.checkStudentSolution("PROMPT_CHECK_STUDENT_SOLUTION_CORRECT")).thenReturn(chatResponse);

        ResponseEntity<String> response = makingTheModelThinkController.checkStudentSolution("PROMPT_CHECK_STUDENT_SOLUTION_CORRECT");

        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedResponse, response.getBody());
        verify(makingTheModelThinkService).checkStudentSolution("PROMPT_CHECK_STUDENT_SOLUTION_CORRECT");
    }

    @Test
    void test_incorrect_checkStudentSolution() {
        String expectedResponse = "AI is working";
        AssistantMessage expectedAssistantMessage = new AssistantMessage(expectedResponse);

        // Create a valid ChatResponse
        ChatResponse chatResponse = new ChatResponse(List.of(new Generation(expectedAssistantMessage)));
        when(makingTheModelThinkService.checkStudentSolution("PROMPT_CHECK_STUDENT_SOLUTION_INCORRECT")).thenReturn(chatResponse);

        ResponseEntity<String> response = makingTheModelThinkController.checkStudentSolution("PROMPT_CHECK_STUDENT_SOLUTION_INCORRECT");

        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedResponse, response.getBody());
        verify(makingTheModelThinkService).checkStudentSolution("PROMPT_CHECK_STUDENT_SOLUTION_INCORRECT");
    }

    @Test
    void test_checkStudentSolution_with_wrong_parameter() {
        ResponseEntity<String> response = makingTheModelThinkController.checkStudentSolution("WRONG_PARAM");

        assertEquals(BAD_REQUEST.value(), response.getStatusCode().value());
        assertEquals("Invalid promptName. Allowed values are: PROMPT_CHECK_STUDENT_SOLUTION_INCORRECT, PROMPT_CHECK_STUDENT_SOLUTION_CORRECT",
            response.getBody());
        verify(makingTheModelThinkService, times(0)).checkStudentSolution("WRONG_PARAM");
    }

    @Test
    void test_summarizeAndTranslate() {
        String expectedResponse = "AI is working";
        AssistantMessage expectedAssistantMessage = new AssistantMessage(expectedResponse);
        // Create a valid ChatResponse
        ChatResponse chatResponse = new ChatResponse(List.of(new Generation(expectedAssistantMessage)));
        when(makingTheModelThinkService.summarizeAndTranslate()).thenReturn(chatResponse);

        ResponseEntity<String> response = makingTheModelThinkController.summarizeAndTranslate();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedResponse, response.getBody());
        verify(makingTheModelThinkService).summarizeAndTranslate();
    }



}