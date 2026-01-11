package ch.guru.spring.ai.prompt.dto;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;

public record Conversation(
    Prompt prompt,
    ChatResponse chatResponse) {

    public static final String PROMPT_CHECK_AI = "2+2? Short.";
}
