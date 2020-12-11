package com.spring.boot.project.demo.controllers.unit;

import static org.junit.Assert.assertEquals;

import com.spring.boot.project.demo.controllers.ProductController;
import com.spring.boot.project.demo.dto.product.ProductDto;
import com.spring.boot.project.demo.dto.product.ProductMapper;
import com.spring.boot.project.demo.model.Product;
import com.spring.boot.project.demo.service.ProductService;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @InjectMocks
    private ProductController productController;
    @Mock
    private ProductService productService;
    @Mock
    private ProductMapper productMapper;

    @Test
    public void getMostCommentedProductTest() {
        Product product1 = new Product();
        product1.setProductId("productId1");
        ProductDto productDto1 = new ProductDto();
        productDto1.setProductId("productId1");
        Product product2 = new Product();
        product2.setProductId("productId2");
        ProductDto productDto2 = new ProductDto();
        productDto2.setProductId("productId2");
        List<Product> productList = List.of(product1, product2);
        Mockito.when(productMapper.convertToProductDto(product1)).thenReturn(productDto1);
        Mockito.when(productMapper.convertToProductDto(product2)).thenReturn(productDto2);
        List<ProductDto> productDtoList = List.of(productDto1, productDto2);
        Mockito.when(productService.findMostCommentedProduct(2)).thenReturn(productList);
        assertEquals(productDtoList, productController.getMostCommentedProduct(2));
    }

    @Test
    public void getAllProducts() {
        Product product1 = new Product();
        product1.setProductId("productId1");
        ProductDto productDto1 = new ProductDto();
        productDto1.setProductId("productId1");
        Product product2 = new Product();
        product2.setProductId("productId2");
        ProductDto productDto2 = new ProductDto();
        productDto2.setProductId("productId2");
        Product product3 = new Product();
        product3.setProductId("productId3");
        ProductDto productDto3 = new ProductDto();
        productDto3.setProductId("productId3");
        List<Product> productList = List.of(product1, product2, product3);
        Mockito.when(productService.findAll()).thenReturn(productList);
        Mockito.when(productMapper.convertToProductDto(product1)).thenReturn(productDto1);
        Mockito.when(productMapper.convertToProductDto(product2)).thenReturn(productDto2);
        Mockito.when(productMapper.convertToProductDto(product3)).thenReturn(productDto3);
        List<ProductDto> productDtoList = List.of(productDto1, productDto2, productDto3);
        assertEquals(productDtoList, productController.getAllProduct());
    }

    @Test
    public void getByProductId() {
        Product product = new Product();
        product.setProductId("productId");
        ProductDto productDto = new ProductDto();
        productDto.setProductId("productId");
        Mockito.when(productService.findById("productId")).thenReturn(product);
        Mockito.when(productMapper.convertToProductDto(product)).thenReturn(productDto);
        assertEquals(productDto, productController.getByProductId("productId"));
    }
}
