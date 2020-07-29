package com.spring.boot.project.demo.controllers;

import com.spring.boot.project.demo.dto.product.ProductDto;
import com.spring.boot.project.demo.dto.product.ProductMapper;
import com.spring.boot.project.demo.service.ProductService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/mostcommented/{numberOfProducts}")
    public List<ProductDto> getMostCommentedProduct(@PathVariable int numberOfProducts) {
        return productService.findMostCommentedProduct(numberOfProducts).stream()
                .map(productMapper::convertToAmazonUserDto).collect(Collectors.toList());
    }
}
