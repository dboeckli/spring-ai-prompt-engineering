package guru.springframework.spring6aiintro.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

import static guru.springframework.spring6aiintro.service.AiResponseFormatter.INPUT_CHECK_AI;

@Service
@Slf4j
public class ChatClientServiceImpl implements ChatClientService {

    private final ChatClient chatClient;

    public ChatClientServiceImpl(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @Override
    public String checkAi() throws JsonProcessingException {
        String input = INPUT_CHECK_AI;
        ChatResponse chatResponse = chatClient.prompt()
            .user(input)
            .call()
            .chatResponse();
        return AiResponseFormatter.formatAiCheckResponse(chatResponse, input);
    }
}
