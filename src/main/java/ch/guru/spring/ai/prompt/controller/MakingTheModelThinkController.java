package ch.guru.spring.ai.prompt.controller;

import ch.guru.spring.ai.prompt.service.MakingTheModelThinkService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static ch.guru.spring.ai.prompt.controller.constants.MakingTheModelThinkConstants.getAvailablePromptNamesForStudentSolution;

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

    @PostMapping("/check-student-solution")
    public ResponseEntity<String> checkStudentSolution(@RequestBody StudentSolutionRequest request) {
        Set<String> availablePrompts = getAvailablePromptNamesForStudentSolution();

        if (availablePrompts.contains(request.getPromptName())) {
            ChatResponse chatResponse = makingTheModelThinkService.checkStudentSolution(request.getPromptName());
            return ResponseEntity.ok(chatResponse.getResult().getOutput().getText());
        } else {
            String errorMessage = "Invalid promptName. Allowed values are: " + String.join(", ", availablePrompts);
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }

    @Data
    @Builder
    public static class StudentSolutionRequest {
        private String promptName;
    }

    @GetMapping("/summarize-and-translate")
    public ResponseEntity<String> summarizeAndTranslate() {
        ChatResponse chatResponse = makingTheModelThinkService.summarizeAndTranslate();
        return ResponseEntity.ok(chatResponse.getResult().getOutput().getText());
    }

}
