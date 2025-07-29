package guru.springframework.spring6aiintro.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ChatClientService {
    String checkAi() throws JsonProcessingException;
}
