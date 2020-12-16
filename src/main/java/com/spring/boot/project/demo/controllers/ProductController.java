package com.spring.boot.project.demo.controllers;

import com.spring.boot.project.demo.dto.product.ProductDto;
import com.spring.boot.project.demo.dto.product.ProductMapper;
import com.spring.boot.project.demo.service.ProductService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @ApiOperation(value = "Finding most commented products.",
            notes = "Default numbers of products to find is 1000.")
    @GetMapping("/most-commented-products")
    public List<ProductDto> getMostCommentedProduct(
            @RequestParam(defaultValue = "1000") int numberOfProducts) {
        return productService.findMostCommentedProduct(numberOfProducts).stream()
                .map(productMapper::convertToProductDto)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Get all products.")
    @GetMapping
    public List<ProductDto> getAllProduct() {
        return productService.findAll().stream()
                .map(productMapper::convertToProductDto)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Get product by id.")
    @GetMapping("{id}")
    public ProductDto getByProductId(
            @PathVariable(name = "id", required = false) String productId) {
        return productMapper.convertToProductDto(productService.findById(productId));
    }
}
