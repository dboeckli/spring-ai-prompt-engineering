package guru.springframework.springAiPrompts.service;

import org.springframework.ai.chat.model.ChatResponse;

public interface ReviewService {
    ChatResponse createReview(String prompt, String... reviews);
}
