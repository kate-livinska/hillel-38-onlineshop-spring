package ua.shop.repo;

import ua.shop.model.Product;

import java.util.Optional;

public interface ProductRepo {
    boolean addProduct(Product product);
    public Optional<Product> findProductById(int id);
    public Optional<Product> updateProduct(Product product);
    public Optional<Product> deleteProduct(int id);
}
