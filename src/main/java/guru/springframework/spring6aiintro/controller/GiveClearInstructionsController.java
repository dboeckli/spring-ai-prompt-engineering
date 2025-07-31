package guru.springframework.spring6aiintro.controller;

import guru.springframework.spring6aiintro.service.GiveClearInstructionsService;
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
@RequestMapping("/api/instructions")
public class GiveClearInstructionsController {

    private final GiveClearInstructionsService giveClearInstructionsService;

    @GetMapping("/cook-a-steak")
    public ResponseEntity<String> createReview() {
        ChatResponse chatResponse = giveClearInstructionsService.cookASteak();
        return ResponseEntity.ok(chatResponse.getResult().getOutput().getText());
    }

}
