package guru.springframework.springAiPrompts.controller;

import guru.springframework.springAiPrompts.service.MakingTheModelThinkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static guru.springframework.springAiPrompts.controller.constants.MakingTheModelThinkConstants.getAvailablePromptNamesForStudentSolution;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/making-the-model-think")
public class MakingTheModelThinkController {

    private final MakingTheModelThinkService makingTheModelThinkService;

    @GetMapping("/enigma-with-ball")
    public ResponseEntity<String> enigmaWithBall() {
        ChatResponse chatResponse = makingTheModelThinkService.enigmaWithBall();
        return ResponseEntity.ok(chatResponse.getResult().getOutput().getText());
    }

    @GetMapping("/check-student-solution")
    public ResponseEntity<String> checkStudentSolution(@RequestParam String promptName) {
        Set<String> availablePrompts = getAvailablePromptNamesForStudentSolution();

        if (availablePrompts.contains(promptName)) {
            ChatResponse chatResponse = makingTheModelThinkService.checkStudentSolution(promptName);
            return ResponseEntity.ok(chatResponse.getResult().getOutput().getText());
        } else {
            String errorMessage = "Invalid promptName. Allowed values are: " + String.join(", ", availablePrompts);
            return ResponseEntity.badRequest().body(errorMessage);
        }

    }

    @GetMapping("/summarize-and-translate")
    public ResponseEntity<String> summarizeAndTranslate() {
        ChatResponse chatResponse = makingTheModelThinkService.summarizeAndTranslate();
        return ResponseEntity.ok(chatResponse.getResult().getOutput().getText());
    }

}
