package ch.guru.spring.ai.prompt.controller.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class GiveClearInstructionsConstants {

    public final static String PROMPT_LIST_OF_CARS = """
        You are a car data generator.
        Generate a list of exactly 4 made-up cars in <%format%> format.
        Each car must have these attributes: make, model, year (between 2020-2030), and color.
        
        Format specific instructions:
        For XML format:
        - Start with exactly: <?xml version="1.0" encoding="UTF-8"?>
        - Use a root element <cars>
        - Each car should be in a <car> element
        - Attributes should be elements: <make>, <model>, <year>, <color>
        
        For JSON format:
        - Start with { and end with }
        - Use "cars" as root array
        - Format: {"cars": [{"make": "...", "model": "...", "year": YYYY, "color": "..."}]}
        - Year must be a number, not a string
        
        For YAML format:
        - Start with "cars:"
        - Each car must be a list item starting with "-"
        - Each car should have make:, model:, year:, and color: as properties
        - Use proper YAML indentation (2 spaces)
        
        Return ONLY the raw content WITHOUT any code blocks, markdown, or explanatory text.
        """;


    //ask the model to check if conditions are satisfied
    public final static String PROMPT_ENUMERATE_INSTRUCTIONS = """
        You will be provided with text delimited by triple quotes.
        If it contains a sequence of instructions,
        re-write those instructions in the following format:
        
        Step 1 - ...
        Step 2 - ...
        Step N - ...
        
        Ensure that the first sentence of your response starts with "Step 1".
        If the text does not contain a sequence of instructions, then simply write \\"No steps provided.\\"
        
        \\"\\"\\"{text_1}\\"\\"\\"
        """;

    public final static String TEXT_COOK_A_STEAK = """
        Cooking the perfect steak is easy.
        First, remove the steak from the refrigerator and packaging. Let sit at
        room temperature for at least 30 mins.
        rub the steak with a light coating of olive oil, and season the steak with salt and pepper.
        Next, heat a pan over high heat.
        Then, add the steak to the pan and sear for 3 minutes on each side.
        Finally, let the steak rest for 5 minutes before slicing.
        Enjoy!""";

    public final static String TEXT_BOOK_DESCRIPTION = """
        Book Elon Musk
        When Elon Musk was a kid in South Africa, he was regularly beaten by bullies. One day a group pushed him down some concrete steps and kicked him until his face was a swollen ball of flesh. He was in the hospital for a week. But the physical scars were minor compared to the emotional ones inflicted by his father, an engineer, rogue, and charismatic fantasist.
        
        His father’s impact on his psyche would linger. He developed into a tough yet vulnerable man-child, prone to abrupt Jekyll-and-Hyde mood swings, with an exceedingly high tolerance for risk, a craving for drama, an epic sense of mission, and a maniacal intensity that was callous and at times destructive.
        
        At the beginning of 2022—after a year marked by SpaceX launching thirty-one rockets into orbit, Tesla selling a million cars, and him becoming the richest man on earth—Musk spoke ruefully about his compulsion to stir up dramas. “I need to shift my mindset away from being in crisis mode, which it has been for about fourteen years now, or arguably most of my life,” he said.""";


}
