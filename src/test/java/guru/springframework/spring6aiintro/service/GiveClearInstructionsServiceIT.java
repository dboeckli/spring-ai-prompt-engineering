package guru.springframework.spring6aiintro.service;

import guru.springframework.spring6aiintro.test.config.DeepseekApiKeyExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("local")
@ExtendWith(DeepseekApiKeyExtension.class)
@Slf4j
class GiveClearInstructionsServiceIT {

    @Autowired
    GiveClearInstructionsService giveClearInstructionsService;

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