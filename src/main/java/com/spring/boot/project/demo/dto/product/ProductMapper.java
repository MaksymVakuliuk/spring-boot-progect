package com.spring.boot.project.demo.dto.product;

import com.spring.boot.project.demo.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDto convertToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(product.getProductId());
        return productDto;
    }

    public Product convertFromProductDto(ProductDto productDto) {
        Product product = new Product();
        product.setProductId(productDto.getProductId());
        return product;
    }
}
