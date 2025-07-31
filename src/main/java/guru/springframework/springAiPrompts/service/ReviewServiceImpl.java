package guru.springframework.springAiPrompts.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    private final ChatModel chatModel;

    @Override
    public ChatResponse createReview(String prompt, String... reviews) {
        PromptTemplate promptTemplate = new PromptTemplate(prompt);

        Map<String, Object> reviewMap = new HashMap<>();
        AtomicInteger counter = new AtomicInteger(1);
        Arrays.stream(reviews).forEach(review ->
            reviewMap.put("review" + counter.getAndIncrement(), review)
        );

        ChatResponse chatResponse = chatModel.call(promptTemplate.create(reviewMap));

        try {
            String json = AiResponseFormatter.formatAiCheckResponse(chatResponse, prompt);
            log.info("Formatted AI response: {}", json);
        } catch (JsonProcessingException e) {
            log.error("Error formatting AI response: {}", e.getMessage());
        }

        return chatResponse;
    }

}
