package guru.springframework.springAiPrompts.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import guru.springframework.springAiPrompts.service.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/question")
public class QuestionController {

    private final OpenAIService openAIService;

    @GetMapping("/check-ai")
    public ResponseEntity<String> checkAi() {
        try {
            String result = openAIService.checkAi();
            return ResponseEntity.ok(result);
        } catch (JsonProcessingException e) {
            return ResponseEntity.internalServerError().body("Error processing AI check: " + e.getMessage());
        }
    }


}
