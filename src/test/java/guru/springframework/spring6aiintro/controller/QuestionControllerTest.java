package guru.springframework.spring6aiintro.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import guru.springframework.spring6aiintro.service.OpenAIService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class QuestionControllerTest {

    @Mock
    private OpenAIService openAIService;

    @InjectMocks
    private QuestionController questionController;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void checkAi_Success() throws JsonProcessingException {
        String expectedResponse = "AI is working";
        when(openAIService.checkAi()).thenReturn(expectedResponse);

        ResponseEntity<String> response = questionController.checkAi();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedResponse, response.getBody());
        verify(openAIService).checkAi();
    }

    @Test
    void checkAi_HandlesException() throws JsonProcessingException {
        String errorMessage = "Parse error";
        when(openAIService.checkAi()).thenThrow(new JsonProcessingException(errorMessage) {});

        ResponseEntity<String> response = questionController.checkAi();

        assertEquals(500, response.getStatusCode().value());
        assertEquals("Error processing AI check: " + errorMessage, response.getBody());
        verify(openAIService).checkAi();
    }

}
