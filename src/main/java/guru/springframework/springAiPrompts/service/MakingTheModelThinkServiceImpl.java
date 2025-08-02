package guru.springframework.springAiPrompts.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

import static guru.springframework.springAiPrompts.controller.constants.MakingTheModelThinkConstants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class MakingTheModelThinkServiceImpl implements MakingTheModelThinkService {

    private final ChatModel chatModel;

    private final ObjectMapper objectMapper;

    @Override
    public ChatResponse summarizeAndTranslate() {
        PromptTemplate promptTemplate = new PromptTemplate(PROMPT_1_SUMMARIZE_AND_TRANSLATE);

        ChatResponse chatResponse = chatModel.call(promptTemplate.create(Map.of("text_1", STORY)));
        try {
            log.info("ChatResponse:\n" + objectMapper.writeValueAsString(chatResponse));
        } catch (JsonProcessingException e) {
            log.error("Error formatting ChatResponse: {}", e.getMessage());
        }
        return chatResponse;
    }

    @Override
    public ChatResponse checkStudentSolution(String prompt) {
        PromptTemplate promptTemplate = new PromptTemplate(prompt);

        ChatResponse chatResponse = chatModel.call(promptTemplate.create());
        try {
            log.info("ChatResponse:\n" + objectMapper.writeValueAsString(chatResponse));
        } catch (JsonProcessingException e) {
            log.error("Error formatting ChatResponse: {}", e.getMessage());
        }
        return chatResponse;
    }

    @Override
    public ChatResponse enigmaWithBall() {
        PromptTemplate promptTemplate = new PromptTemplate(PROMPT_4_ENIGMA_WITH_BALL);

        ChatResponse chatResponse = chatModel.call(promptTemplate.create());
        try {
            log.info("ChatResponse:\n" + objectMapper.writeValueAsString(chatResponse));
        } catch (JsonProcessingException e) {
            log.error("Error formatting ChatResponse: {}", e.getMessage());
        }
        return chatResponse;
    }
}
