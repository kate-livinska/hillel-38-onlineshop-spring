package ua.shop.repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.shop.model.Product;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryImplTest {
    private ProductRepo repo;

    @BeforeEach
    void setUp() {
        repo = new ProductRepositoryImpl();
    }
    @Test
    void addProductTest_returnsProductWithID() {
        Product product = new Product(null, "test", 100.8);
        Product result = repo.addProduct(product);

        assertNotNull(result);
        assertEquals(product.getName(), result.getName());
        assertEquals(product.getPrice(), result.getPrice());
    }

    @Test
    void addProductTest_presentId_throwsException() {
        Product product = new Product(7L, "test", 100.8);
        assertThrows(IllegalArgumentException.class, () -> repo.addProduct(product));
    }

    @Test
    void findProductTest_returnsOptProduct() {
        Optional<Product> result = repo.findProductById(1L);
        assertTrue(result.isPresent());
        assertEquals(result.get().getId(), 1L);
    }

    @Test
    void findProductTest_noProductWithId_returnsEmptyOptional() {
        Optional<Product> result = repo.findProductById(0L);
        assertFalse(result.isPresent());
    }

    @Test
    void updateProductTest_returnsUpdatedProduct() {
        Product product = new Product(1L, "testTest", 50.5);
        Optional<Product> result = repo.updateProduct(product);
        assertTrue(result.isPresent());
        assertEquals(product.getName(), result.get().getName());
        assertEquals(product.getPrice(), result.get().getPrice());

    }

    @Test
    void updateProductTest_noId_returnsEmptyOptional() {
        Product product = new Product(null, "testTest", 50.5);
        Optional<Product> result = repo.updateProduct(product);
        assertFalse(result.isPresent());
    }

    @Test
    void deleteProductTest_returnsDeletedProduct() {
        Optional<Product> result = repo.deleteProduct(1L);
        assertNotNull(result);
        assertEquals(1L, result.get().getId());
        assertEquals(2, repo.getProducts().size());
    }

    @Test
    void deleteProductTest_noSuchProduct_returnEmptyOptional() {
        Optional<Product> result = repo.deleteProduct(0L);
        assertFalse(result.isPresent());
    }
}