package ch.guru.spring.ai.prompt.controller;

import ch.guru.spring.ai.prompt.dto.Conversation;
import ch.guru.spring.ai.prompt.service.OpenAIService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
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
class QuestionControllerTest {

    @Mock
    private OpenAIService openAIService;

    @InjectMocks
    private QuestionController questionController;

    @BeforeEach
    void setUp() {
        try (AutoCloseable ignored = openMocks(this)) {
            log.info("Setting up mocks for QuestionControllerTest");
        } catch (Exception e) {
            throw new RuntimeException("Failed to open mocks", e);
        }
    }

    @Test
    void checkAi_Success() {
        String expectedResponse = "AI is working";
        AssistantMessage expectedAssistantMessage = new AssistantMessage(expectedResponse);
        ChatResponse exptectedChatResponse = new ChatResponse(List.of(new Generation(expectedAssistantMessage)));

        Conversation expectedConversation = new Conversation(null , exptectedChatResponse);
        when(openAIService.checkAi()).thenReturn(expectedConversation);

        ResponseEntity<Conversation> responseEntity = questionController.checkAi();
        Assertions.assertNotNull(responseEntity.getBody());
        String responseText = responseEntity.getBody()
            .chatResponse()
            .getResult()
            .getOutput()
            .getText();

        assertEquals(200, responseEntity.getStatusCode().value());
        assertEquals(expectedResponse, responseText);
        verify(openAIService).checkAi();
    }
}
