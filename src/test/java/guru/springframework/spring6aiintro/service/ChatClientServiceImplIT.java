package guru.springframework.spring6aiintro.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.spring6aiintro.test.config.DeepseekApiKeyExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


@SpringBootTest
@ActiveProfiles("local")
@ExtendWith(DeepseekApiKeyExtension.class)
@Slf4j
class ChatClientServiceImplIT {

    @Autowired
    ChatClientService chatClientService;

    @Test
    void testCheckAi() {
        String response = assertDoesNotThrow(() -> chatClientService.checkAi());
        assertThat(response, allOf(
            notNullValue(),
            not(emptyString())
        ));

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);

            if (jsonNode.isObject() && jsonNode.has("result")) {
                response = jsonNode.get("result").asText();
            }
        } catch (JsonProcessingException e) {
            log.info("Response is not a valid JSON. Treating it as a plain string.");
        }

        assertThat(response, allOf(
            containsString("4")
        ));
    }
}