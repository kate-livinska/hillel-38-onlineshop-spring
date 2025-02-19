package ua.shop.service;

import lombok.Data;
import ua.shop.model.Product;
import ua.shop.repo.ProductRepo;
import ua.shop.repo.ProductRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class Cart {
    private final ProductRepo repo;
    private final List<Product> productList;

    public Cart(ProductRepo productRepository) {
        this.repo = productRepository;
        this.productList = new ArrayList<>();
    }
    public boolean addProduct(int id) {
        Optional<Product> product = repo.findProductById(id);
        if (product.isPresent()) {
            productList.add(product.get());
            return true;
        }
        return false;
    }

    public boolean removeProduct(int id) {
        Optional<Product> product = repo.findProductById(id);
        if (product.isPresent()) {
            productList.remove(product.get());
            return true;
        }

        return false;
    }
}
