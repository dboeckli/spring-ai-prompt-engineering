package guru.springframework.springAiPrompts.service;

import guru.springframework.springAiPrompts.test.config.DeepseekApiKeyExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("local")
@ExtendWith(DeepseekApiKeyExtension.class)
@Slf4j
class MakingTheModelThinkServiceImplIT {

    @Autowired
    MakingTheModelThinkService makingTheModelThinkService;

    @Test
    void testEnigmaWithBall() {
        ChatResponse chatResponse = assertDoesNotThrow(() -> makingTheModelThinkService.enigmaWithBall());
        assertNotNull(chatResponse);

        String response = chatResponse.getResult().getOutput().getText();

        // Get the last line of the response
        assertNotNull(response);
        String lastLine = response.lines().reduce((first, second) -> second).orElse("");

        // Assert that the last line is exactly as expected
        assertThat("The last line should be the expected solution",
            lastLine.trim(),
            equalTo("SOLUTION: The ball is on the table outside the microwave."));
    }

    @Test
    void testCheckStudentSolutionGivenCorrectSolution() {
        ChatResponse chatResponse = assertDoesNotThrow(() -> makingTheModelThinkService
            .checkStudentSolution("PROMPT_CHECK_STUDENT_SOLUTION_CORRECT"));
        assertNotNull(chatResponse);

        String response = chatResponse.getResult().getOutput().getText();
        log.info("Student solution response: {}", response);

        assertThat(response, containsString("Solution was: Incorrect"));
    }

    @Test
    void testCheckStudentSolutionGivenWrongSolution() {
        ChatResponse chatResponse = assertDoesNotThrow(() -> makingTheModelThinkService
            .checkStudentSolution("PROMPT_CHECK_STUDENT_SOLUTION_INCORRECT"));
        assertNotNull(chatResponse);

        String response = chatResponse.getResult().getOutput().getText();
        log.info("Student solution response: {}", response);

        assertThat(response, containsString("Solution was: Incorrect"));
    }

    @Test
    void testSummarizeAndTranslate() {
        ChatResponse chatResponse = assertDoesNotThrow(() -> makingTheModelThinkService.summarizeAndTranslate());
        assertNotNull(chatResponse);

        String response = chatResponse.getResult().getOutput().getText();
        log.info("Summarized and translated response: {}", response);

        assertThat(response, containsString("Jack i Jill"));
    }

}