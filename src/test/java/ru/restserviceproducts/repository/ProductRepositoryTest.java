package ru.restserviceproducts.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import ru.restserviceproducts.entity.Product;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
public class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    public void findByActiveTrueAndIdTest() {
        Product productFound = productRepository.findByActiveTrueAndId(1L).orElse(null);
        assertThat(productFound).isNotNull();
    }

    @Test
    public void findByActiveTrueAndIdBadTest() {
        Product productFound = productRepository.findByActiveTrueAndId(5L).orElse(null);
        assertThat(productFound).isNull();
    }

    @Test
    public void findByActiveTrueTest() {
        List<Product> productList = productRepository.findByActiveTrue();
        assertThat(productList).isNotEmpty();
    }

    @Test
    public void findProductForClientTest() {
        List<Product> productList = productRepository.findProductForClient(30000L, 20000L, true);
        assertThat(productList).isNotEmpty();
    }

    @Test
    public void findProductForClientBadTest() {
        List<Product> productList = productRepository.findProductForClient(3000000L, 2000000L, true);
        assertThat(productList).isEmpty();
    }
}
