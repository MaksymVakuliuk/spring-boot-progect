package com.spring.boot.project.demo.controllers;

import com.spring.boot.project.demo.dto.review.ReviewFromDbDto;
import com.spring.boot.project.demo.dto.review.ReviewMapper;
import com.spring.boot.project.demo.dto.review.ReviewRequestDto;
import com.spring.boot.project.demo.model.Review;
import com.spring.boot.project.demo.service.ReviewService;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
@AllArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @ApiOperation(value = "Finding most used words in reviews.",
            notes = "Default numbers of words to find is 1000.")
    @GetMapping("/most-used-words")
    public List<String> getMostUsedWords(@RequestParam(defaultValue = "1000") int numberOfWords) {
        return reviewService.findMostUsedWords(numberOfWords);
    }

    @ApiOperation(value = "Show all reviews.")
    @GetMapping("all")
    public List<ReviewFromDbDto> getAll() {
        return reviewService.findAll().stream()
                .map(reviewMapper::convertReviewToReviewFromDbDto)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Save product's review to database.")
    @PostMapping("/save")
    public ReviewFromDbDto saveReview(@Valid @RequestBody ReviewRequestDto reviewRequestDto) {
        Review review = reviewMapper.convertReviewRequestDtoToReview(reviewRequestDto);
        review.setTime(LocalDateTime.now());
        return reviewMapper.convertReviewToReviewFromDbDto(reviewService.save(review));
    }

    @ApiOperation(value = "Update product's review to database.")
    @PostMapping("/update")
    public ReviewFromDbDto updateReview(@Valid @RequestBody ReviewFromDbDto reviewFromDbDto) {
        Review review = reviewMapper.convertReviewDbDtoToReview(reviewFromDbDto);
        review.setTime(LocalDateTime.now());
        return reviewMapper.convertReviewToReviewFromDbDto(reviewService.save(review));
    }

    @ApiOperation(value = "Delete product's review to database.")
    @GetMapping("/remove/{id}")
    public String deleteReview(@PathVariable(name = "id") String reviewId) {
        reviewService.deleteById(Long.valueOf(reviewId));
        return "Review with id " + reviewId + " was deleted.";
    }
}
