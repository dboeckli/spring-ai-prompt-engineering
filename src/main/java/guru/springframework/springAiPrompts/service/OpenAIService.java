package guru.springframework.springAiPrompts.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface OpenAIService {
    String checkAi() throws JsonProcessingException;
}
