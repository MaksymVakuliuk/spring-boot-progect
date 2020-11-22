package com.spring.boot.project.demo.service.impl;

import static org.junit.Assert.assertEquals;

import com.spring.boot.project.demo.model.Product;
import com.spring.boot.project.demo.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductServiceImpl productServiceImpl;
    private List<Product> productList;

    @Before
    public void setup() {
        Product product1 = new Product();
        product1.setProductId("productId1");
        Product product2 = new Product();
        product2.setProductId("productId2");
        productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
    }

    @Test
    public void save() {
        Product product = productList.get(0);
        Mockito.when(productRepository.save(product)).thenReturn(product);
        Product savedProduct = productServiceImpl.save(product);
        assertEquals(product, savedProduct);
    }

    @Test
    public void saveAll() {
        Mockito.when(productRepository.saveAll(productList)).thenReturn(productList);
        List<Product> savedProductList = productServiceImpl.saveAll(productList);
        assertEquals(productList, savedProductList);
    }

    @Test
    public void findById() {
        Product product = productList.get(0);
        Mockito.when(productRepository.findById("productId1")).thenReturn(Optional.of(product));
        Product productById = productServiceImpl.findById("productId1");
        assertEquals(product, productById);
    }

    @Test
    public void findAll() {
        Mockito.when(productRepository.findAll()).thenReturn(productList);
        List<Product> productListFromDb = productServiceImpl.findAll();
        assertEquals(productList, productListFromDb);
    }

    @Test
    public void findMostCommentedProduct() {
        Mockito.when(productRepository.findMostCommentedProduct(PageRequest.of(0,1)))
                .thenReturn(productList.subList(0,1));
        List<Product> mostCommentedProduct = productServiceImpl.findMostCommentedProduct(1);
        assertEquals(productList.subList(0,1), mostCommentedProduct);
    }
}
