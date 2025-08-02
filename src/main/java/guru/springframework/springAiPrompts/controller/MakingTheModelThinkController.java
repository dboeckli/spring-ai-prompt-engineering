package guru.springframework.springAiPrompts.controller;

import guru.springframework.springAiPrompts.service.MakingTheModelThinkService;
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
@RequestMapping("/api/making-the-model-think")
public class MakingTheModelThinkController {

    private final MakingTheModelThinkService makingTheModelThinkService;

    @GetMapping("/enigma-with-ball")
    public ResponseEntity<String> enigmaWithBall() {
        ChatResponse chatResponse = makingTheModelThinkService.enigmaWithBall();
        return ResponseEntity.ok(chatResponse.getResult().getOutput().getText());
    }

}
