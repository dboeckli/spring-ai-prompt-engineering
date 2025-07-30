package guru.springframework.spring6aiintro.controller;

import guru.springframework.spring6aiintro.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static guru.springframework.spring6aiintro.constants.ReviewConstants.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/create-review")
    public ResponseEntity<String> createReview() {
        ChatResponse chatResponse = reviewService.createReview(REVIEW_PROMPT, REVIEW_1);
        return ResponseEntity.ok(chatResponse.getResult().getOutput().getText());
    }

    @GetMapping("/create-reviews")
    public ResponseEntity<String> createReviews() {
        ChatResponse chatResponse = reviewService.createReview(REVIEW_PROMPT_3, REVIEW_1, REVIEW_2, REVIEW_3);
        return ResponseEntity.ok(chatResponse.getResult().getOutput().getText());
    }

    @GetMapping("/create-reviews-extract")
    public ResponseEntity<String> createDescriptionFromReviewsExtract() {
        ChatResponse chatResponse = reviewService.createReview(REVIEW_PROMPT_4, REVIEW_1, REVIEW_2, REVIEW_3);
        return ResponseEntity.ok(chatResponse.getResult().getOutput().getText());
    }

}
