package guru.springframework.springAiPrompts.service;

import guru.springframework.springAiPrompts.test.config.DeepseekApiKeyExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static guru.springframework.springAiPrompts.controller.constants.ReviewConstants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("local")
@ExtendWith(DeepseekApiKeyExtension.class)
@Slf4j
class ReviewServiceImplIT {

    @Autowired
    ReviewService reviewService;

    @Test
    void testCreateReview() {
        ChatResponse chatResponse = assertDoesNotThrow(() -> reviewService.createReview(REVIEW_PROMPT, REVIEW_1));
        String response = chatResponse.getResult().getOutput().getText();

        log.info("response is: " + response);

        assertNotNull(response);
        final int sizeToCheck;
        if (response.contains("(" + MAX_SUMMARY_REVIEW_PROMPT_WORDS + " words)")) {
            sizeToCheck = MAX_SUMMARY_REVIEW_PROMPT_WORDS + ("(" + MAX_SUMMARY_REVIEW_PROMPT_WORDS + ")").length();
        } else {
            sizeToCheck = MAX_SUMMARY_REVIEW_PROMPT_WORDS + 2;
        }

        String[] words = response.split("\\s+");

        assertAll("Review Response Validierung",

            () -> assertThat("Response should contain at most 30 words. Response was: " + response,
                words.length, lessThanOrEqualTo(sizeToCheck)),

            () -> assertThat("Response should not contain backticks",
                response, not(containsString("```"))),

            () -> assertThat("Response should mention the book title",
                response, containsStringIgnoringCase("Elon Musk")),
            () -> assertThat("Response should mention the author",
                response, containsStringIgnoringCase("Walter Isaacson")),

            () -> assertThat("Response should mention biography or life",
                response, anyOf(
                    containsStringIgnoringCase("biography"),
                    containsStringIgnoringCase("life")
                )),
            () -> assertThat("Response should mention innovation or visionary",
                response, anyOf(
                    containsStringIgnoringCase("innovation"),
                    containsStringIgnoringCase("visionary")
                ))
        );
    }

    @Test
    void testCreateReviews() {
        ChatResponse chatResponse = assertDoesNotThrow(() -> reviewService.createReview(REVIEW_PROMPT_3, REVIEW_1, REVIEW_2, REVIEW_3));
        String response = chatResponse.getResult().getOutput().getText();

        log.info("response is: " + response);
        assertNotNull(response);
        final int sizeToCheck;
        if (response.contains("(" + MAX_SUMMARY_REVIEW_PROMPT_3_WORDS +  " words)")) {
            sizeToCheck = MAX_SUMMARY_REVIEW_PROMPT_3_WORDS + ("(" + MAX_SUMMARY_REVIEW_PROMPT_3_WORDS + ")").length();
        } else {
            sizeToCheck = MAX_SUMMARY_REVIEW_PROMPT_3_WORDS + 5;
        }

        assertAll("Review Response Validierung",
            () -> assertNotNull(response, "Response should not be null"),
            () -> assertThat("Response should contain at most 200 words. Response was: " + response,
                response.split("\\s+").length, lessThanOrEqualTo(sizeToCheck)),
            () -> assertThat("Response should not contain review markers",
                response, not(containsString("Review"))),
            () -> assertThat("Response should contain insights from all reviews",
                response, allOf(
                    containsStringIgnoringCase("Elon Musk"),
                    containsStringIgnoringCase("Walter Isaacson"),
                    containsStringIgnoringCase("biography"),
                    anyOf(
                        containsStringIgnoringCase("technology"),
                        containsStringIgnoringCase("innovation"),
                        containsStringIgnoringCase("visionary")
                    )
                ))
        );
    }


    @Test
    void testDescriptionFromReviewsExtract() {
        ChatResponse chatResponse = assertDoesNotThrow(() -> reviewService.createReview(REVIEW_PROMPT_4, REVIEW_1, REVIEW_2, REVIEW_3));
        String response = chatResponse.getResult().getOutput().getText();

        log.info("response is: " + response);
        assertNotNull(response);
        final int sizeToCheck;
        if (response.contains("(" + MAX_SUMMARY_REVIEW_PROMPT_4_WORDS + " words)")) {
            sizeToCheck = MAX_SUMMARY_REVIEW_PROMPT_4_WORDS + ("(" + MAX_SUMMARY_REVIEW_PROMPT_4_WORDS + ")").length();
        } else {
            sizeToCheck = MAX_SUMMARY_REVIEW_PROMPT_4_WORDS + 5;
        }

        assertAll("Review Response Validierung",
            () -> assertNotNull(response, "Response should not be null"),
            () -> assertThat("Response should contain at most 200 words. Response was: " + response,
                response.split("\\s+").length, lessThanOrEqualTo(sizeToCheck)),
            () -> assertThat("Response should be well-formatted",
                response, not(containsString("```"))),
            () -> assertThat("Response should contain key book information",
                response, allOf(
                    containsStringIgnoringCase("Elon Musk"),
                    containsStringIgnoringCase("Walter Isaacson")
                )),
            () -> assertThat("Response should include content from multiple reviews",
                response, anyOf(
                    containsStringIgnoringCase("SpaceX"),
                    containsStringIgnoringCase("Tesla"),
                    containsStringIgnoringCase("AI"),
                    containsStringIgnoringCase("innovation")
                ))
        );
    }

}
