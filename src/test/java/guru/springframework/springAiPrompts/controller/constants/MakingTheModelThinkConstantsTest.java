package guru.springframework.springAiPrompts.controller.constants;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MakingTheModelThinkConstantsTest {

    @Test
    void getAvailablePromptNamesForStudentSolution() {
        Set<String> promptVariableNames = MakingTheModelThinkConstants.getAvailablePromptNamesForStudentSolution();
        assertEquals(2, promptVariableNames.size());

        for (String promptName : promptVariableNames) {
            try {
                Field field = MakingTheModelThinkConstants.class.getDeclaredField(promptName);
                String promptValue = (String) field.get(null);

                boolean isValidPrompt = promptValue.equals(MakingTheModelThinkConstants.PROMPT_CHECK_STUDENT_SOLUTION_INCORRECT) ||
                    promptValue.equals(MakingTheModelThinkConstants.PROMPT_CHECK_STUDENT_SOLUTION_CORRECT);

                assertTrue(isValidPrompt, "Prompt " + promptName +
                    " should match either PROMPT_CHECK_STUDENT_SOLUTION_INCORRECT or PROMPT_CHECK_STUDENT_SOLUTION_CORRECT");
            } catch (NoSuchFieldException | IllegalAccessException e) {
                fail("Failed to access field " + promptName + ": " + e.getMessage());
            }
        }


    }
}