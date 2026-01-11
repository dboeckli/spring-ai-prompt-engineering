package ch.guru.spring.ai.prompt.service;

import ch.guru.spring.ai.prompt.controller.constants.InferenceConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

import static ch.guru.spring.ai.prompt.controller.constants.InferenceConstants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class InferenceServiceImpl implements InferenceService {

    private final ChatModel chatModel;

    private final ObjectMapper objectMapper;

    @Override
    public ChatResponse getSentimentsOfReview() {
        PromptTemplate promptTemplate = new PromptTemplate(PROMPT_SENTIMENT);

        ChatResponse chatResponse = chatModel.call(promptTemplate.create(Map.of(
            "review1", REVIEW_1,
            "review2", REVIEW_2,
            "review3", REVIEW_3,
            "review4", REVIEW_4,
            "review5", REVIEW_5,
            "review6", REVIEW_6)));
        try {
            log.info("ChatResponse:\n" + objectMapper.writeValueAsString(chatResponse));
        } catch (JsonProcessingException e) {
            log.error("Error formatting ChatResponse: {}", e.getMessage());
        }
        return chatResponse;
    }

    @Override
    public ChatResponse getEmotionsOfReview() {
        PromptTemplate promptTemplate = new PromptTemplate(PROMPT_EMOTION);

        ChatResponse chatResponse = chatModel.call(promptTemplate.create(Map.of(
            "review1", REVIEW_1,
            "review2", REVIEW_2,
            "review3", REVIEW_3,
            "review4", REVIEW_4,
            "review5", REVIEW_5,
            "review6", REVIEW_6)));
        try {
            log.info("ChatResponse:\n" + objectMapper.writeValueAsString(chatResponse));
        } catch (JsonProcessingException e) {
            log.error("Error formatting ChatResponse: {}", e.getMessage());
        }
        return chatResponse;
    }

    @Override
    public ChatResponse getAngersOfReview() {
        PromptTemplate promptTemplate = new PromptTemplate(PROMPT_ANGER);

        ChatResponse chatResponse = chatModel.call(promptTemplate.create(Map.of(
            "review1", REVIEW_1,
            "review2", REVIEW_2,
            "review3", REVIEW_3,
            "review4", REVIEW_4,
            "review5", REVIEW_5,
            "review6", REVIEW_6)));
        try {
            log.info("ChatResponse:\n" + objectMapper.writeValueAsString(chatResponse));
        } catch (JsonProcessingException e) {
            log.error("Error formatting ChatResponse: {}", e.getMessage());
        }
        return chatResponse;
    }

    @Override
    public ChatResponse inferTopics() {
        PromptTemplate promptTemplate = new PromptTemplate(PROMPT_STORY);

        ChatResponse chatResponse = chatModel.call(promptTemplate.create(Map.of("story", InferenceConstants.STORY)));
        try {
            log.info("ChatResponse:\n" + objectMapper.writeValueAsString(chatResponse));
        } catch (JsonProcessingException e) {
            log.error("Error formatting ChatResponse: {}", e.getMessage());
        }
        return chatResponse;

    }
}
