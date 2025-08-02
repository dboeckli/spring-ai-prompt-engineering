package guru.springframework.springAiPrompts.service;

import guru.springframework.springAiPrompts.dto.Conversation;
import guru.springframework.springAiPrompts.test.config.DeepseekApiKeyExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@ActiveProfiles("local")
@ExtendWith(DeepseekApiKeyExtension.class)
@Slf4j
class ChatClientServiceImplIT {

    @Autowired
    ChatClientService chatClientService;

    @Test
    void testCheckAi() {
        Conversation conversation = assertDoesNotThrow(() -> chatClientService.checkAi());

        assertNotNull(conversation);

        assertThat(conversation.chatResponse().getResult().getOutput().getText(), allOf(
            containsString("4")
        ));
    }
}