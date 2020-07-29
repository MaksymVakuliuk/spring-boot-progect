package com.spring.boot.project.demo.repository;

import com.spring.boot.project.demo.model.Product;
import java.util.List;
import java.util.stream.Collectors;

import com.spring.boot.project.demo.service.DbService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {
    private static final String REVIEWS_CSV_FILE_PATH
            = "src/test/resources/tests/util/reviews_test.csv";
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private DbService dbService;

    @Before
    public void setup() {
        dbService.clearDb();
        dbService.initializeDb(REVIEWS_CSV_FILE_PATH);
    }


    @Test
    public void getMostCommentedProduct() {
        List<Product> mostCommentedProduct
                = productRepository.findMostCommentedProduct(PageRequest.of(0, 1));
        assertEquals("product1",
                mostCommentedProduct.stream()
                        .map(Product::getProductId)
                        .collect(Collectors.joining(", ")));
    }
}
