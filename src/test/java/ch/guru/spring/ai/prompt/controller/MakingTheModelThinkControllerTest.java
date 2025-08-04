package ch.guru.spring.ai.prompt.controller;

import ch.guru.spring.ai.prompt.controller.MakingTheModelThinkController.StudentSolutionRequest;
import ch.guru.spring.ai.prompt.service.MakingTheModelThinkService;
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

        StudentSolutionRequest studentSolutionRequest = StudentSolutionRequest.builder()
            .promptName("PROMPT_CHECK_STUDENT_SOLUTION_CORRECT")
            .build();
        ResponseEntity<String> response = makingTheModelThinkController.checkStudentSolution(studentSolutionRequest);

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

        StudentSolutionRequest studentSolutionRequest = StudentSolutionRequest.builder()
            .promptName("PROMPT_CHECK_STUDENT_SOLUTION_INCORRECT")
            .build();
        ResponseEntity<String> response = makingTheModelThinkController.checkStudentSolution(studentSolutionRequest);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedResponse, response.getBody());
        verify(makingTheModelThinkService).checkStudentSolution("PROMPT_CHECK_STUDENT_SOLUTION_INCORRECT");
    }

    @Test
    void test_checkStudentSolution_with_wrong_parameter() {
        StudentSolutionRequest studentSolutionRequest = StudentSolutionRequest.builder()
            .promptName("WRONG_PARAM")
            .build();
        ResponseEntity<String> response = makingTheModelThinkController.checkStudentSolution(studentSolutionRequest);

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