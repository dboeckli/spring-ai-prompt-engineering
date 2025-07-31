package guru.springframework.spring6aiintro.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


@SpringBootTest
@ActiveProfiles("local")
@Slf4j
class OpenAIServiceImplIT {

    @Autowired
    OpenAIService openAIService;

    @BeforeAll
    static void setup() throws IOException {
        Path envFile = Paths.get(".run", ".deepseekapi-key-env");
        if (Files.exists(envFile)) {
            List<String> lines = Files.readAllLines(envFile);
            for (String line : lines) {
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    System.setProperty(parts[0], parts[1]);
                }
            }
        } else {
            log.info("Warning: .deepseekapi-key-env file not found. Ensure it exists or set DEEPSEEK_API_KEY manually.");
        }
    }

    @Test
    void testCheckAi() {
        String response = assertDoesNotThrow(() -> openAIService.checkAi());
        assertThat(response, allOf(
            Matchers.notNullValue(),
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
