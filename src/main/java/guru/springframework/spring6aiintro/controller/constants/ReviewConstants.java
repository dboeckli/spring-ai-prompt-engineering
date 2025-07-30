package guru.springframework.spring6aiintro.controller.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class ReviewConstants {

    public final static String REVIEW_1 = """
        "Elon Musk" by Walter Isaacson is an extraordinary biographical exploration of one of the most fascinating and
        innovative figures of our time. As an admirer of Elon Musk and his ventures, I found this book to be an incredibly
        insightful and inspiring read that goes far beyond the typical biography. Here's why I believe it's a must-read for
        anyone interested in technology, entrepreneurship, and the future of humanity.
        
        Thorough and In-Depth Research:
        Walter Isaacson is renowned for his meticulous research and ability to provide a comprehensive account of his
        subjects. In "Elon Musk," he delves deep into Musk's life, from his childhood in South Africa to his founding of
        multiple groundbreaking companies like SpaceX and Tesla. The book leaves no stone unturned, offering a detailed
        and well-rounded portrait of this visionary entrepreneur.
        
        Humanizing the Genius:
        Isaacson's writing shines in its ability to humanize Musk, a man often seen as an enigmatic genius. The book
        delves into Musk's personal struggles, his successes, and his vulnerabilities, allowing readers to relate to
        him on a human level. This approach makes the story all the more engaging and relatable.
        
        Awe-Inspiring Vision:
        Musk's vision for the future is nothing short of awe-inspiring, and Isaacson does an exceptional job of conveying
        the magnitude of Musk's ambitions. From colonizing Mars to revolutionizing the automotive industry, Musk's visionary
        ideas are portrayed with enthusiasm and intellectual depth. Reading about his endeavors leaves you feeling
        invigorated and excited about the possibilities of our future.
        
        Insights into the Creative Process:
        "Elon Musk" offers valuable insights into the creative process of a brilliant mind. The book details Musk's
        relentless pursuit of innovation and his willingness to take risks that others deemed impossible. For aspiring
        entrepreneurs and innovators, the book provides a treasure trove of lessons on perseverance, problem-solving,
        and thinking beyond conventional boundaries.
        
        Compelling Narrative Style:
        Walter Isaacson's storytelling skills are evident throughout the book. His ability to craft a compelling
        narrative makes this biography read more like an adventure novel. The prose flows seamlessly, keeping the reader
        engaged and eager to turn the page.
        
        Timely and Relevant:
        In an era where technology and the future of our planet are at the forefront of global discussions, "Elon Musk"
        is incredibly timely and relevant. The book not only provides a window into Musk's life but also addresses
        pressing issues like sustainable energy, space exploration, and artificial intelligence.
        
        In conclusion, "Elon Musk" by Walter Isaacson is an exceptional biography that offers a profound and intimate
        look at the life and mind of a modern visionary. It's a testament to the power of human determination, innovation,
        and audacious dreams. Whether you're an Elon Musk enthusiast or simply curious about the world-changing ideas
        of our time, this book is a captivating and enlightening journey that is not to be missed. I highly recommend
        it as a must-read for anyone seeking inspiration and insight into the future.""";

    public final static String REVIEW_2 = """
        I finally had the chance to read Walter Isaacson’s latest book on Elon Musk over the holidays. This book is
        more than just a biography; it offers a masterclass in the mindset and process of a tech revolutionary who
        challenges the status quo and redefines what's possible.
        
        This engaging read delves into Musk's innovative work - from space exploration and sleek electric car designs
        to satellite internet and AI advancements. The narrative provides an insight into Musk's thought process, highlighting
        his strategic thinking, learn-by-trying approach, problem-solving skills, and bold decision-making.
        
        Isaacson's approach is both educational and inspiring, simplifying the details of Musk's BIG-scale projects while
        maintaining the key elements of their groundbreaking impact. The book transforms his sequential innovation
        into a practical guide for the art of possibility exploration and idea development, accessible to readers
        from all walks of life.
        
        The structure of the book, with short and readable chapters, enhances understanding and keeps you engaged.
        Isaacson’s thorough research and extensive interviews unlock the deeper significance of Musk's projects, beyond
        just technology. It enables readers to abstract the complexity of his work and extract valuable lessons
        applicable to business, the creative thought process, and even personal growth.
        
        While the book is comprehensive, I wish it had delved deeper into Elon Musk's insights on AI. Given his pivotal
        roles in OpenAI and xAI GROK, readers would find the book even more valuable with a more extensive exploration
        of Musk's perspectives on AI.
        """;

    public final static String REVIEW_3 = """
        An excellent biography of an exceptional person. Elon Musk has been incredibly successful is diverse directions.
        This book gave insight into what has driven him. Like Steve Jobs, Musk is absolutely focused on the end product
        with minimal concern about the path. Musk is not satisfied when a product merely meets its initial specifications;
        it must also accomplish that by the most efficient means. And he doesn't fear taking risks along the way.
        It seems impossible that a single person could have accomplished what Musk has done. This book goes a long way to
        reveal how he came to be the way he is, how he operates and what drives him. It would be very hard to live with
        such a person and this seems fairly well documented. The purchase of Twitter/X is particularly interesting- his
        end goal was to end the 'woke' movement and encourage 'free speech', but things got complicated, and not helped
        by Musk's propensity to do stupid things (a recurring theme).
        The world is very lucky to have Elon Musk. But its complicated..
        Anyhow, reading this well written book provides insight to one of the most productive people of our time.
        I recommend it highly.""";

    public final static int MAX_SUMMARY_REVIEW_PROMPT_WORDS = 30;

    public final static String REVIEW_PROMPT = """
        Your task is to generate a short summary for a book from an ecommerce site. The summary will be used for a
        web page selling the book.
        
        IMPORTANT RULES:
        1. The summary MUST be EXACTLY %d words or LESS. This is a STRICT and CRITICAL requirement.
        2. Include the book title and author name.
        3. Focus on the most important aspects only.
        4. Use concise language.
        5. Count your words carefully before submitting.
        
        Review: ```{review1}```
        
        CRITICAL: Your response MUST NOT exceed %d words under ANY circumstances. If your summary is longer, shorten it.
        
        """.formatted(MAX_SUMMARY_REVIEW_PROMPT_WORDS, MAX_SUMMARY_REVIEW_PROMPT_WORDS);

    public final static int MAX_SUMMARY_REVIEW_PROMPT_3_WORDS = 200;

    public final static String REVIEW_PROMPT_3 = """
        Your task is to generate a summary for a book from reviews. The summary will be used for a
        web page selling the book. You will be given 3 reviews. Create the summary based on the reviews and
        include information in the summary from all 3 reviews.
        
        Summarize the reviews below, delimited by triple backticks, in at most %d words.
        
        Review: ```{review1}```
        
        Review 2: ```{review2}```
        
        Review 3: ```{review3}```
        """.formatted(MAX_SUMMARY_REVIEW_PROMPT_3_WORDS);

    public final static int MAX_SUMMARY_REVIEW_PROMPT_4_WORDS = 200;

    public final static String REVIEW_PROMPT_4 = """
        Your task is to extract a summary for a book from reviews. The summary will be used for a
        web page selling the book. You will be given 3 reviews. Create the summary based on the reviews and
        include information in the summary from all 3 reviews.
        
        Summarize the reviews below, delimited by triple backticks, in at most %d words.
        
        Review: ```{review1}```
        
        Review 2: ```{review2}```
        
        Review 3: ```{review3}```
        """.formatted(MAX_SUMMARY_REVIEW_PROMPT_4_WORDS);
}
