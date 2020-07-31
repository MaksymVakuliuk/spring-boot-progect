package com.spring.boot.project.demo.controllers;

import com.spring.boot.project.demo.service.ReviewService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
@AllArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @ApiOperation(value = "Finding most used words in reviews.",
            notes = "Default numbers of words to find is 1000.")
    @GetMapping("/most-used-words")
    public List<String> getMostUsedWords(@RequestParam(defaultValue = "1000") int numberOfWords) {
        return reviewService.findMostUsedWords(numberOfWords);
    }
}
