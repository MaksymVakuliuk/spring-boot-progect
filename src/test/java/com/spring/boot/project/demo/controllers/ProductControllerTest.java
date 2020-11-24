package com.spring.boot.project.demo.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.spring.boot.project.demo.dto.product.ProductDto;
import com.spring.boot.project.demo.dto.product.ProductMapper;
import com.spring.boot.project.demo.model.Product;
import com.spring.boot.project.demo.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    private static final String REQUEST = "/products/most-commented-products";
    @MockBean
    private ProductService productService;
    @MockBean
    private ProductMapper productMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user", password = "pass", roles = "USER")
    public void getMostCommentedProductTest() throws Exception {
        Product product1 = new Product();
        product1.setProductId("productId1");
        Product product2 = new Product();
        product2.setProductId("productId2");
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        ProductDto productDto1 = new ProductDto();
        productDto1.setProductId("productId1");
        ProductDto productDto2 = new ProductDto();
        productDto2.setProductId("productId2");
        Mockito.when(productService.findMostCommentedProduct(2)).thenReturn(productList);
        Mockito.when(productMapper.convertToProductDto(product1)).thenReturn(productDto1);
        Mockito.when(productMapper.convertToProductDto(product2)).thenReturn(productDto2);
        mockMvc.perform(get(REQUEST).param("numberOfProducts", "2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[1].productId", Matchers.is("productId2")));
    }
}
