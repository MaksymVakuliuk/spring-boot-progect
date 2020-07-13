package com.spring.boot.project.demo.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private AmazonUser amazonUser;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    @Column(name = "HelpfulnessNumerator", unique = true, nullable = false)
    private int helpfulnessNumerator;
    @Column(name = "HelpfulnessDenominator", unique = true, nullable = false)
    private int helpfulnessDenominator;
    @Column(name = "Score", unique = true, nullable = false)
    private int score;
    @Column(name = "Time", nullable = false)
    private LocalDateTime time;
    @Column(name = "Summary", nullable = false)
    private String summary;
    @Column(name = "Text", nullable = false)
    private String text;
}
