package guru.springframework.springAiPrompts.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

import static guru.springframework.springAiPrompts.controller.constants.GiveClearInstructionsConstants.PROMPT_REFORMAT_INSTRUCTIONS;

@Service
@RequiredArgsConstructor
@Slf4j
public class GiveClearInstructionsServiceImpl implements GiveClearInstructionsService {

    private final ChatModel chatModel;

    @Override
    public ChatResponse cookASteak(String instructions) {
        PromptTemplate promptTemplate = new PromptTemplate(PROMPT_REFORMAT_INSTRUCTIONS);

        ChatResponse chatResponse = chatModel.call(promptTemplate.create(Map.of("text_1", instructions)));

        try {
            String json = AiResponseFormatter.formatAiCheckResponse(chatResponse, PROMPT_REFORMAT_INSTRUCTIONS);
            log.info("Formatted AI response: {}", json);
        } catch (JsonProcessingException e) {
            log.error("Error formatting AI response: {}", e.getMessage());
        }

        return chatResponse;
    }
}
