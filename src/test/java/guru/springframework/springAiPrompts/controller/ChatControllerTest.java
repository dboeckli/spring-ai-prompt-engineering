package guru.springframework.springAiPrompts.controller;

import guru.springframework.springAiPrompts.dto.Conversation;
import guru.springframework.springAiPrompts.service.ChatClientService;
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

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@Slf4j
class ChatControllerTest {

    @Mock
    private ChatClientService chatService;

    @InjectMocks
    private ChatController chatController;

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
        // Given
        String expectedResponse = "AI is working";
        AssistantMessage expectedAssistantMessage = new AssistantMessage(expectedResponse);
        ChatResponse exptectedChatResponse = new ChatResponse(List.of(new Generation(expectedAssistantMessage)));

        Conversation expectedConversation = new Conversation(null , exptectedChatResponse);
        when(chatService.checkAi()).thenReturn(expectedConversation);

        // When
        ResponseEntity<Conversation> responseEntity = chatController.checkAi();
        Assertions.assertNotNull(responseEntity.getBody());
        String responseText = responseEntity.getBody()
            .chatResponse()
            .getResult()
            .getOutput()
            .getText();

        // Then
        assertAll("AI Check Erfolgsfall",
            () -> assertEquals(200, responseEntity.getStatusCode().value()),
            () -> assertEquals(expectedResponse, responseText),
            () -> verify(chatService).checkAi()
        );
    }
}