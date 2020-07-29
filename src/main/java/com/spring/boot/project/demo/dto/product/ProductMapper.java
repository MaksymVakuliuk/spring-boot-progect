package com.spring.boot.project.demo.dto.product;

import com.spring.boot.project.demo.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDto convertToAmazonUserDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(product.getProductId());
        return productDto;
    }

    public Product convertFromAmazonUserDto(ProductDto productDto) {
        Product product = new Product();
        product.setProductId(productDto.getProductId());
        return product;
    }
}
