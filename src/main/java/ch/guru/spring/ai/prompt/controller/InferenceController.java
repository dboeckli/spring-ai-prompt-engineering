package ch.guru.spring.ai.prompt.controller;

import ch.guru.spring.ai.prompt.service.InferenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/inference")
public class InferenceController {

    private final InferenceService inferenceService;

    @GetMapping("/get-sentiments")
    public ResponseEntity<String> getSentiments() {
        ChatResponse chatResponse = inferenceService.getSentimentsOfReview();
        return ResponseEntity.ok(chatResponse.getResult().getOutput().getText());
    }

    @GetMapping("/get-emotions")
    public ResponseEntity<String> getEmotions() {
        ChatResponse chatResponse = inferenceService.getEmotionsOfReview();
        return ResponseEntity.ok(chatResponse.getResult().getOutput().getText());
    }

    @GetMapping("/get-angers")
    public ResponseEntity<String> getAngers() {
        ChatResponse chatResponse = inferenceService.getAngersOfReview();
        return ResponseEntity.ok(chatResponse.getResult().getOutput().getText());
    }

    @GetMapping("/get-infer-topics")
    public ResponseEntity<String> getInferTopics() {
        ChatResponse chatResponse = inferenceService.inferTopics();
        return ResponseEntity.ok(chatResponse.getResult().getOutput().getText());
    }

}
