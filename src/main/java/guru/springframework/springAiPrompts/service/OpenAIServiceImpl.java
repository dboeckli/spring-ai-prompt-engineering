package guru.springframework.springAiPrompts.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import static guru.springframework.springAiPrompts.service.AiResponseFormatter.INPUT_CHECK_AI;


@Service
@RequiredArgsConstructor
@Slf4j
public class OpenAIServiceImpl implements OpenAIService {

    private final ChatModel chatModel;

    @Override
    public String checkAi() throws JsonProcessingException {
        String input = INPUT_CHECK_AI;
        Prompt promptObj = new Prompt(new UserMessage(input));
        promptObj.getInstructions().forEach(m -> log.info("role={}, text={}", m.getMessageType(), m.getText()));
        ChatResponse chatResponse = chatModel.call(promptObj);
        return AiResponseFormatter.formatAiCheckResponse(chatResponse, input);
    }
}
