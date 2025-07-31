package guru.springframework.springAiPrompts.controller;

import guru.springframework.springAiPrompts.service.GiveClearInstructionsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static guru.springframework.springAiPrompts.controller.constants.GiveClearInstructionsConstants.TEXT_COOK_A_STEAK;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/instructions")
public class GiveClearInstructionsController {

    private final GiveClearInstructionsService giveClearInstructionsService;

    @GetMapping("/cook-a-steak")
    public ResponseEntity<String> cookASteak() {
        ChatResponse chatResponse = giveClearInstructionsService.cookASteak(TEXT_COOK_A_STEAK);
        return ResponseEntity.ok(chatResponse.getResult().getOutput().getText());
    }

}
