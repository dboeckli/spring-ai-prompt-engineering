package ch.guru.spring.ai.prompt.controller;

import ch.guru.spring.ai.prompt.dto.Conversation;
import ch.guru.spring.ai.prompt.service.OpenAIService;
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
    public ResponseEntity<Conversation> checkAi() {
        Conversation conversation = openAIService.checkAi();
        return ResponseEntity.ok(conversation);
    }


}
