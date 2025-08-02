package guru.springframework.springAiPrompts.controller;

import guru.springframework.springAiPrompts.service.GiveClearInstructionsService;
import guru.springframework.springAiPrompts.service.GiveClearInstructionsService.ResponseResultFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

import static guru.springframework.springAiPrompts.controller.constants.GiveClearInstructionsConstants.TEXT_BOOK_DESCRIPTION;
import static guru.springframework.springAiPrompts.controller.constants.GiveClearInstructionsConstants.TEXT_COOK_A_STEAK;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/enumerate-instructions")
public class GiveClearInstructionsController {

    private final GiveClearInstructionsService giveClearInstructionsService;

    @GetMapping("/cook-a-steak")
    public ResponseEntity<String> enumerateCookASteakInstructions() {
        ChatResponse chatResponse = giveClearInstructionsService.enumerateInstructions(TEXT_COOK_A_STEAK);
        return ResponseEntity.ok(chatResponse.getResult().getOutput().getText());
    }

    @GetMapping("/book-description")
    public ResponseEntity<String> enumerateBookDescription() {
        ChatResponse chatResponse = giveClearInstructionsService.enumerateInstructions(TEXT_BOOK_DESCRIPTION);
        return ResponseEntity.ok(chatResponse.getResult().getOutput().getText());
    }

    @GetMapping("/list-cars/{format}")
    public ResponseEntity<String> listCars(@PathVariable String format) {
        try {
            ResponseResultFormat enumFormat = ResponseResultFormat.valueOf(format.toUpperCase());
            ChatResponse chatResponse = giveClearInstructionsService.listCars(enumFormat);
            return ResponseEntity.ok(chatResponse.getResult().getOutput().getText());
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                .badRequest()
                .body("Ungültiges Format. Erlaubte Werte sind: " +
                    Arrays.toString(ResponseResultFormat.values()));
        }
    }
}
