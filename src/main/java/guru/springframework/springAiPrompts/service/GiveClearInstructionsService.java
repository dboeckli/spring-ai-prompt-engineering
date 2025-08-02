package guru.springframework.springAiPrompts.service;

import org.springframework.ai.chat.model.ChatResponse;

public interface GiveClearInstructionsService {

    ChatResponse enumerateInstructions(String instructions);

    ChatResponse listCars(ResponseResultFormat format);

    enum ResponseResultFormat {
        JSON,
        XML,
        YAML
    }

}
