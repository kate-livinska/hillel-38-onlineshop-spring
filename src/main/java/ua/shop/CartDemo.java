package ua.shop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.shop.config.AppConfig;
import ua.shop.service.Cart;

public class CartDemo {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Cart cart = context.getBean(Cart.class);

        cart.addProduct(1L);
        cart.addProduct(2L);
        cart.addProduct(3L);
        System.out.println(cart.getProductList());

        cart.removeProduct(2L);
        System.out.println(cart.getProductList());

        Cart cart2 = context.getBean(Cart.class);
        cart2.addProduct(2L);
        System.out.println(cart2.getProductList());
    }
}
