package guru.springframework.springAiPrompts.service;

import guru.springframework.springAiPrompts.dto.Conversation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import static guru.springframework.springAiPrompts.dto.Conversation.PROMPT_CHECK_AI;


@Service
@RequiredArgsConstructor
@Slf4j
public class OpenAIServiceImpl implements OpenAIService {

    private final ChatModel chatModel;

    @Override
    public Conversation checkAi() {
        Prompt prompt = new Prompt(new UserMessage(PROMPT_CHECK_AI));
        ChatResponse chatResponse = chatModel.call(prompt);

        return new Conversation(
            prompt,
            chatResponse
        );
    }
}
