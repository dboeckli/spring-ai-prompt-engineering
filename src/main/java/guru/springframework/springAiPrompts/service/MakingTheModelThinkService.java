package guru.springframework.springAiPrompts.service;

import org.springframework.ai.chat.model.ChatResponse;

public interface MakingTheModelThinkService {

    ChatResponse summarizeAndTranslate();

    ChatResponse checkStudentSolution(String promptName);

    ChatResponse  enigmaWithBall();

}
