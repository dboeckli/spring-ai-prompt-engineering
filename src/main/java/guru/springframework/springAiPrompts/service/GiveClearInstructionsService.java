package guru.springframework.springAiPrompts.service;

import org.springframework.ai.chat.model.ChatResponse;

public interface GiveClearInstructionsService {

    ChatResponse cookASteak(String instructions);

}
