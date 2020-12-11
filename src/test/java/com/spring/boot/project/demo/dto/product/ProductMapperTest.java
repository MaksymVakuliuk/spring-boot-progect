package com.spring.boot.project.demo.dto.product;

import static org.junit.Assert.assertEquals;

import com.spring.boot.project.demo.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProductMapperTest {
    @InjectMocks
    private ProductMapper productMapper;

    @Test
    public void convertToProductDto() {
        Product product = new Product();
        product.setProductId("productId");
        ProductDto productDto = new ProductDto();
        productDto.setProductId(product.getProductId());
        assertEquals(productDto, productMapper.convertToProductDto(product));
    }

    @Test
    public void convertFromProductDto() {
        ProductDto productDto = new ProductDto();
        productDto.setProductId("productId");
        Product product = new Product();
        product.setProductId(productDto.getProductId());
        assertEquals(product, productMapper.convertFromProductDto(productDto));
    }
}
