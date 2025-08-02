package guru.springframework.springAiPrompts.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private final ObjectMapper objectMapper;

    @Override
    public ChatResponse enumerateInstructions(String instructions) {
        PromptTemplate promptTemplate = new PromptTemplate(PROMPT_ENUMERATE_INSTRUCTIONS);

        ChatResponse chatResponse = chatModel.call(promptTemplate.create(Map.of("text_1", instructions)));
        try {
            log.info("ChatResponse:\n" + objectMapper.writeValueAsString(chatResponse));
        } catch (JsonProcessingException e) {
            log.error("Error formatting ChatResponse: {}", e.getMessage());
        }
        return chatResponse;
    }

    @Override
    public ChatResponse listCars(ResponseResultFormat format) {
        String promptString = PROMPT_LIST_OF_CARS.replace("<%format%>", format.toString());
        Prompt prompt = new Prompt(promptString);

        ChatResponse chatResponse = chatModel.call(prompt);

        try {
            log.info("ChatResponse:\n" + objectMapper.writeValueAsString(chatResponse));
        } catch (JsonProcessingException e) {
            log.error("Error formatting ChatResponse: {}", e.getMessage());
        }

        return chatResponse;
    }

}
