package guru.springframework.spring6aiintro.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface OpenAIService {
    String checkAi() throws JsonProcessingException;
}
