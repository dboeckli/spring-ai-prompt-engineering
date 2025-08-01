package guru.springframework.springAiPrompts.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

import static guru.springframework.springAiPrompts.controller.constants.GiveClearInstructionsConstants.PROMPT_ENUMERATE_INSTRUCTIONS;
import static guru.springframework.springAiPrompts.controller.constants.GiveClearInstructionsConstants.PROMPT_LIST_OF_CARS;

@Service
@RequiredArgsConstructor
@Slf4j
public class GiveClearInstructionsServiceImpl implements GiveClearInstructionsService {

    private final ChatModel chatModel;

    @Override
    public ChatResponse enumerateInstructions(String instructions) {
        PromptTemplate promptTemplate = new PromptTemplate(PROMPT_ENUMERATE_INSTRUCTIONS);

        ChatResponse chatResponse = chatModel.call(promptTemplate.create(Map.of("text_1", instructions)));

        try {
            String json = AiResponseFormatter.formatAiCheckResponse(chatResponse, PROMPT_ENUMERATE_INSTRUCTIONS);
            log.info("Formatted AI response: {}", json);
        } catch (JsonProcessingException e) {
            log.error("Error formatting AI response: {}", e.getMessage());
        }

        return chatResponse;
    }

    @Override
    public ChatResponse listCars(ResponseResultFormat format) {
        String promptString = PROMPT_LIST_OF_CARS.replace("<%format%>", format.toString());
        Prompt prompt = new Prompt(promptString);

        ChatResponse chatResponse = chatModel.call(prompt);

        try {
            String json = AiResponseFormatter.formatAiCheckResponse(chatResponse, PROMPT_LIST_OF_CARS);
            log.info("Formatted AI response: {}", json);
        } catch (JsonProcessingException e) {
            log.error("Error formatting AI response: {}", e.getMessage());
        }

        return chatResponse;
    }

}
