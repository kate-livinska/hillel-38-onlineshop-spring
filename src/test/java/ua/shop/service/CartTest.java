package ua.shop.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import ua.shop.repo.TestParent;

import static org.junit.jupiter.api.Assertions.*;

class CartTest extends TestParent {
    @Autowired
    Cart cart;

    @Test
    void addProductTest_productAdded() {
        cart.addProduct(1L);
        assertEquals(1, cart.getProductList().size());
        assertEquals(1L, cart.getProductList().get(0).getId());
    }

    @Test
    void addProductTest_productNotAvailable_returnsFalse() {
        boolean result = cart.addProduct(100L);
        assertFalse(result);
    }

    @Test
    void removeProduct() {
        cart.addProduct(1L);
        cart.addProduct(2L);
        cart.removeProduct(1L);
        assertEquals(1, cart.getProductList().size());
        assertEquals(2L, cart.getProductList().get(0).getId());
    }

    @Test
    void removeProductTest_productNotAvailable_returnsFalse() {
        boolean result = cart.removeProduct(100L);
        assertFalse(result);
    }
}