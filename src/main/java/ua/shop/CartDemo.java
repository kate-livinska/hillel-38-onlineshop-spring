package ua.shop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.shop.config.AppConfig;
import ua.shop.service.Cart;

import java.util.List;

public class CartDemo {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Cart cart = context.getBean(Cart.class);

        cart.addProduct(1);
        cart.addProduct(2);
        cart.addProduct(3);
        System.out.println(cart.getProductList());

        cart.removeProduct(2);
        System.out.println(cart.getProductList());

        Cart cart2 = context.getBean(Cart.class);
        cart2.addProduct(2);
        System.out.println(cart2.getProductList());
    }
}
