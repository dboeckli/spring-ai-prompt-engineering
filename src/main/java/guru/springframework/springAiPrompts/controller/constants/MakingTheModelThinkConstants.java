package guru.springframework.springAiPrompts.controller.constants;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public final class MakingTheModelThinkConstants {

    public final static String STORY = """
        In a charming village, siblings Jack and Jill set out on
        a quest to fetch water from a hilltop well.
        As they climbed, singing joyfully, misfortune
        struck—Jack tripped on a stone and tumbled
        down the hill, with Jill following suit.
        Though slightly battered, the pair returned home to
        comforting embraces. Despite the mishap,
        their adventurous spirits remained undimmed, and they
        continued exploring with delight.
        """;

    public final static String PROMPT_SUMMARIZE_AND_TRANSLATE = """
        Perform the following actions:
        1 - Summarize the following text delimited by triple
        backticks with 1 sentence.
        2 - Translate the summary into Polish.
        3 - List each name in the Polish summary.
        4 - Output a json object that contains the following
        keys: polish_summary, num_names.
        Separate your answers with line breaks.
        Text:
        ```{text}```
        """;

    public final static String PROMPT_CHECK_STUDENT_SOLUTION_INCORRECT = """
        Determine if the student's solution is correct or not.
        
        Question:
        I'm building a solar power installation and I need
         help working out the financials.
        - Land costs $100 / square foot
        - I can buy solar panels for $250 / square foot
        - I negotiated a contract for maintenance that will cost
        me a flat $100k per year, and an additional $10 / square foot
        
        What is the total cost for the first year of operations
        as a function of the number of square feet.
        
        Student's Solution:
        Let x be the size of the installation in square feet.
        Costs:
        1. Land cost: 100x
        2. Solar panel cost: 250x
        3. Maintenance cost: 100,000 + 100x
        Total cost: 100x + 250x + 100,000 + 100x = 450x + 100,000
        
        At the end place the follwoing outcome:
        Solution was: Correct or Incorrect
        """;

    public final String PROMPT_CHECK_STUDENT_SOLUTION_CORRECT = """
        Your task is to determine if the student's solution is correct or not.
        To solve the problem do the following:
        - First, work out your own solution to the problem including the final total.
        - Then compare your solution to the student's solution and evaluate if the student's solution is correct or not.
        
        Don't decide if the student's solution is correct until you have done the problem yourself.
        
        Use the following format:
        Question:
        ```question here```
        
        Student's solution:
        ```student's solution here```
        
        Actual solution:
        ```steps to work out the solution and your solution here```
        
        Is the student's solution the same as actual solution just calculated:
        ```yes or no```
        
        Student grade:
        ```correct or incorrect```
        
        Solution was: Correct or Incorrect
        
        Question:
        ```
        I'm building a solar power installation and I need help working out the financials.
        - Land costs $100 / square foot
        - I can buy solar panels for $250 / square foot
        - I negotiated a contract for maintenance that will cost me a flat $100k per year, and an additional $10 / square foot
        
        What is the total cost for the first year of operations as a function of the number of square feet.
        ```
        
        Student's solution:
        ```
        Let x be the size of the installation in square feet.
        Costs:
        1. Land cost: 100x
        2. Solar panel cost: 250x
        3. Maintenance cost: 100,000 + 100x
        Total cost: 100x + 250x + 100,000 + 100x = 450x + 100,000
        ```
        
        Actual solution:
        ```actual solution here```
        """;

    public static Set<String> getAvailablePromptNamesForStudentSolution() {
        return Arrays.stream(MakingTheModelThinkConstants.class.getDeclaredFields())
            .map(Field::getName)
            .filter(name -> name.startsWith("PROMPT_CHECK_STUDENT_SOLUTION_"))
            .collect(Collectors.toSet());
    }

    public final String PROMPT_ENIGMA_WITH_BALL = """
        You are an expert at solving reasoning problems. A cup is an object with an open top and close on the sides and bottom.
        The open top does not prevent objects from passing through it.
        
        Assume the laws of physics on Earth. A small marble is put into a normal cup and the cup is placed upside down on a
        table, causing the open side of the cup to be in contact with the table. Gravity will cause the ball to fall to the table.
        Someone then picks the cup up without changing its orientation and puts it inside the microwave. Where is the ball
        now. Determine the position of the ball in each step. Explain
        why the ball is positioned where it is.
        
        At the end of your explanation, format a single line starting with "SOLUTION:" that states very briefly where the ball is located.
        use the following solutions:
        - The ball is on the table outside the microwave.
        - The ball is inside the microwave.
        """;

}
