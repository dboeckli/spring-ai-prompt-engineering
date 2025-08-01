package guru.springframework.springAiPrompts.service;

import guru.springframework.springAiPrompts.service.GiveClearInstructionsService.ResponseResultFormat;
import guru.springframework.springAiPrompts.test.config.DeepseekApiKeyExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.stream.Stream;

import static guru.springframework.springAiPrompts.controller.constants.GiveClearInstructionsConstants.TEXT_BOOK_DESCRIPTION;
import static guru.springframework.springAiPrompts.controller.constants.GiveClearInstructionsConstants.TEXT_COOK_A_STEAK;
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

    @ParameterizedTest
    @MethodSource("provideTestParameters")
    void testEnumerateInstructions(String inputText, List<String> expectedPhrases, boolean hasSteps, long minimumSteps) {
        ChatResponse chatResponse = assertDoesNotThrow(() -> giveClearInstructionsService.enumerateInstructions(inputText));
        String response = chatResponse.getResult().getOutput().getText();

        log.info("response is: " + response);

        assertAll(
            () -> assertNotNull(response, "Response should not be null"),
            () -> {
                if (hasSteps) {
                    assertThat(response, matchesPattern("(?is).*step\\s*\\d+.*"));
                    assertNotNull(response);
                    assertThat(
                        response.toLowerCase().lines()
                            .filter(line -> line.matches(".*step\\s*\\d+.*"))
                            .count(),
                        greaterThanOrEqualTo(minimumSteps)
                    );
                } else {
                    assertNotNull(response);
                    assertThat(response.toLowerCase(), containsString("no steps provided"));
                }
            },
            () -> {
                for (String phrase : expectedPhrases) {
                    assertThat(response, containsStringIgnoringCase(phrase));
                }
            }
        );
    }

    private static Stream<Arguments> provideTestParameters() {
        return Stream.of(
            Arguments.of(
                TEXT_COOK_A_STEAK,
                List.of("room temperature", "olive oil", "salt", "pepper", "minutes"),
                true,
                5L
            ),
            Arguments.of(
                TEXT_COOK_A_STEAK + " Give the directions using the tone of Snoop Dog",
                List.of("room temp", "olive oil", "salt", "pepper", "min"),
                true,
                5L
            ),
            Arguments.of(
                TEXT_COOK_A_STEAK + " Give the directions using the tone, tools and imagination of JK Rowling in a Harry Potter book",
                List.of("room temp", "olive oil", "salt", "pepper", "min"),
                true,
                5L
            ),
            Arguments.of(
                TEXT_BOOK_DESCRIPTION,
                List.of("No steps provided"),
                false,
                0L
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideFormatTestParameters")
    void testListCars(ResponseResultFormat format) {
        log.info("list cars in format: " + format);

        ChatResponse chatResponse = assertDoesNotThrow(() -> giveClearInstructionsService.listCars(format));

        String response = chatResponse.getResult().getOutput().getText();

        log.info("response is: " + response);
        assertNotNull(response);
        switch (format) {
            case XML -> assertAll("XML Format",
                () -> assertTrue(response.startsWith("<?xml"), "XML sollte mit <?xml beginnen"),
                () -> assertThat(response, containsString("<cars>")),
                () -> assertThat(response, containsString("<car>")),
                () -> assertEquals(4, countOccurrences(response, "<car>"))
            );
            case JSON -> assertAll("JSON Format",
                () -> assertTrue(response.trim().startsWith("{"), "JSON sollte mit { beginnen"),
                () -> assertTrue(response.trim().endsWith("}"), "JSON sollte mit } enden"),
                () -> assertThat(response, containsString("\"cars\"")),
                () -> assertThat(response, containsString("\"make\"")),
                () -> assertThat(response, containsString("\"model\"")),
                () -> assertThat(response, containsString("\"year\"")),
                () -> assertThat(response, containsString("\"color\"")),
                () -> assertEquals(4, countOccurrences(response, "\"make\""))
            );
            case YAML -> assertAll("YAML Format",
                () -> assertTrue(response.trim().startsWith("cars:"), "YAML sollte mit cars: beginnen"),
                () -> assertThat(response, containsString("make:")),
                () -> assertThat(response, containsString("model:")),
                () -> assertThat(response, containsString("year:")),
                () -> assertThat(response, containsString("color:")),
                () -> assertEquals(4, countOccurrences(response, "make:"))
            );
        }

    }

    private static Stream<Arguments> provideFormatTestParameters() {
        return Stream.of(
            Arguments.of(ResponseResultFormat.XML),
            Arguments.of(ResponseResultFormat.JSON),
            Arguments.of(ResponseResultFormat.YAML)
        );
    }

    private int countOccurrences(String str, String searchStr) {
        return (str.length() - str.replace(searchStr, "").length()) / searchStr.length();
    }
}