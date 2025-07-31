package guru.springframework.spring6aiintro.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.model.ChatResponse;
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
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("local")
@Slf4j
class GiveClearInstructionsServiceIT {

    @Autowired
    GiveClearInstructionsService giveClearInstructionsService;

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
    void testCookASteak() {
        ChatResponse chatResponse = assertDoesNotThrow(() -> giveClearInstructionsService.cookASteak());
        String response = chatResponse.getResult().getOutput().getText();

        log.info("response is: " + response);

        assertAll(
            () -> assertNotNull(response, "Response should not be null"),
            () -> assertThat(response, containsString("Step 1")),
            () -> assertThat(response, containsStringIgnoringCase("room temperature")),
            () -> assertThat(response, containsStringIgnoringCase("olive oil")),
            () -> assertThat(response, containsStringIgnoringCase("salt")),
            () -> assertThat(response, containsStringIgnoringCase("pepper")),
            () -> assertThat(response, containsStringIgnoringCase("minutes")),
            () -> {
                assertNotNull(response);
                assertThat(
                    response.lines()
                        .filter(line -> line.trim().startsWith("Step"))
                        .count(),
                    greaterThanOrEqualTo(5L)
                );
            }
        );

    }


}