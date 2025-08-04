package ch.guru.spring.ai.prompt.controller;

import ch.guru.spring.ai.prompt.dto.Conversation;
import ch.guru.spring.ai.prompt.service.ChatClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatClientService chatClientService;

    @GetMapping("/check-ai")
    public ResponseEntity<Conversation> checkAi() {
        Conversation conversation = chatClientService.checkAi();
        return ResponseEntity.ok(conversation);
    }

}
