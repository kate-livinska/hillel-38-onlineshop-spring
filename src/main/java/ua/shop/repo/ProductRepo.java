package ua.shop.repo;

import ua.shop.model.Product;


import java.util.List;
import java.util.Optional;

public interface ProductRepo {
    Product addProduct(Product product);
    Optional<Product> findProductById(Long id);
    Optional<Product> updateProduct(Product product);
    Optional<Product> deleteProduct(Long id);
    List<Product> getProducts();
}
