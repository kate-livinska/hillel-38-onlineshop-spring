package ua.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ua.shop.repo.ProductRepo;
import ua.shop.repo.ProductRepositoryImpl;
import ua.shop.service.Cart;

@Configuration
public class TestConfig {
    @Bean
    public ProductRepo productRepo() {
        return new ProductRepositoryImpl();
    }

    @Bean
    @Scope("prototype")
    public Cart cart(final ProductRepo productRepository) {
        return new Cart(productRepository);
    }
}
