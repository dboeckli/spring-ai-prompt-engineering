package ch.guru.spring.ai.prompt.service;

import org.springframework.ai.chat.model.ChatResponse;

public interface InferenceService {

    ChatResponse getSentimentsOfReview();

    ChatResponse getEmotionsOfReview();

    ChatResponse getAngersOfReview();

    ChatResponse inferTopics();

}
