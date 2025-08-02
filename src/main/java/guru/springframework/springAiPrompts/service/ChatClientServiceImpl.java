package guru.springframework.springAiPrompts.service;

import guru.springframework.springAiPrompts.dto.Conversation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import static guru.springframework.springAiPrompts.dto.Conversation.PROMPT_CHECK_AI;

@Service
@Slf4j
public class ChatClientServiceImpl implements ChatClientService {

    private final ChatClient chatClient;

    public ChatClientServiceImpl(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @Override
    public Conversation checkAi() {
        ChatResponse chatResponse = chatClient.prompt()
            .user(PROMPT_CHECK_AI)
            .call()
            .chatResponse();

        return new Conversation(
            new Prompt(new UserMessage(PROMPT_CHECK_AI)),
            chatResponse
        );
    }
}
