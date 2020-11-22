package com.spring.boot.project.demo.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @Column(nullable = false, unique = true)
    private String productId;
    @OneToMany(mappedBy = "product")
    private Set<Review> reviews;
}
