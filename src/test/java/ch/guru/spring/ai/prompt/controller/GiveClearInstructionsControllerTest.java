package ch.guru.spring.ai.prompt.controller;

import ch.guru.spring.ai.prompt.service.GiveClearInstructionsService;
import ch.guru.spring.ai.prompt.service.GiveClearInstructionsService.ResponseResultFormat;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Stream;

import static ch.guru.spring.ai.prompt.controller.constants.GiveClearInstructionsConstants.TEXT_BOOK_DESCRIPTION;
import static ch.guru.spring.ai.prompt.controller.constants.GiveClearInstructionsConstants.TEXT_COOK_A_STEAK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.mockito.internal.verification.VerificationModeFactory.times;

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
    void test_enumerateCookASteakInstructions() {
        String expectedResponse = "AI is working";
        AssistantMessage expectedAssistantMessage = new AssistantMessage(expectedResponse);
        // Create a valid ChatResponse
        ChatResponse chatResponse = new ChatResponse(List.of(new Generation(expectedAssistantMessage)));
        when(giveClearInstructionsService.enumerateInstructions(TEXT_COOK_A_STEAK)).thenReturn(chatResponse);

        ResponseEntity<String> response = giveClearInstructionsController.enumerateCookASteakInstructions();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedResponse, response.getBody());
        verify(giveClearInstructionsService).enumerateInstructions(TEXT_COOK_A_STEAK);
    }

    @Test
    void test_enumerateBookDescription() {
        String expectedResponse = "AI is working";
        AssistantMessage expectedAssistantMessage = new AssistantMessage(expectedResponse);
        // Create a valid ChatResponse
        ChatResponse chatResponse = new ChatResponse(List.of(new Generation(expectedAssistantMessage)));
        when(giveClearInstructionsService.enumerateInstructions(TEXT_BOOK_DESCRIPTION)).thenReturn(chatResponse);

        ResponseEntity<String> response = giveClearInstructionsController.enumerateBookDescription();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedResponse, response.getBody());
        verify(giveClearInstructionsService).enumerateInstructions(TEXT_BOOK_DESCRIPTION);
    }

    @ParameterizedTest
    @MethodSource("provideFormatsForTesting")
    void test_listCars(String format, int expectedStatus) {
        String expectedResponse = "AI is working";
        AssistantMessage expectedAssistantMessage = new AssistantMessage(expectedResponse);
        // Create a valid ChatResponse
        ChatResponse chatResponse = new ChatResponse(List.of(new Generation(expectedAssistantMessage)));
        when(giveClearInstructionsService.listCars(any())).thenReturn(chatResponse);

        ResponseEntity<String> response = giveClearInstructionsController.listCars(format);

        assertEquals(expectedStatus, response.getStatusCode().value());
        assertEquals(expectedResponse, response.getBody());
        verify(giveClearInstructionsService).listCars(any());
    }

    @Test
    void test_listCars_wrong_format() {
        String expectedResponse = "AI is working";
        AssistantMessage expectedAssistantMessage = new AssistantMessage(expectedResponse);
        // Create a valid ChatResponse
        ChatResponse chatResponse = new ChatResponse(List.of(new Generation(expectedAssistantMessage)));
        when(giveClearInstructionsService.listCars(any())).thenReturn(chatResponse);

        ResponseEntity<String> response = giveClearInstructionsController.listCars("WRONG_FORMAT");

        assertEquals(400, response.getStatusCode().value());
        assertEquals("Ungültiges Format. Erlaubte Werte sind: [JSON, XML, YAML]", response.getBody());
        verify(giveClearInstructionsService, times(0)).listCars(any());
    }

    private static Stream<Arguments> provideFormatsForTesting() {
        return Stream.of(
            Arguments.of(ResponseResultFormat.JSON.name(), 200),
            Arguments.of(ResponseResultFormat.XML.name(), 200),
            Arguments.of(ResponseResultFormat.YAML.name(), 200)
        );
    }

}