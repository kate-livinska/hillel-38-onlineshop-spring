package ua.shop.repo;

import lombok.Data;
import org.springframework.stereotype.Repository;
import ua.shop.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Data
public class ProductRepositoryImpl implements ProductRepo {
    private List<Product> products;

    public ProductRepositoryImpl() {
        products = new ArrayList<>();
        Product product1 = new Product(1L, "Laptop", 999.99);
        Product product2 = new Product(2L, "Smartphone", 699.99);
        Product product3 = new Product(3L, "Headphones", 199.99);

        List<Product> products = List.of(product1, product2, product3);
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public boolean addProduct(Product product) {
        if (product.getId() != null) {
            throw new IllegalArgumentException("Product id already exists");
        }
        if (!products.contains(product)) {
            Long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
            product.setId(id);
            products.add(product);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Product> findProductById(int id) {
        Long idLong = (long) id;
        return products.stream()
                .filter(p -> p.getId().equals(idLong))
                .findFirst();
    }

    @Override
    public Optional<Product> updateProduct(Product product) {
        if (product != null && product.getId() != null && products.contains(product)) {
            Optional<Product> found = products.stream()
                    .filter(p -> p.getId().equals(product.getId()))
                    .findFirst();
            found.ifPresent(p -> {
                        p.setName(product.getName());
                        p.setPrice(product.getPrice());
                    });
            return found;
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Product> deleteProduct(int id) {
        Long idLong = (long) id;

        Optional<Product> found = products.stream()
                .filter(p -> p.getId().equals(idLong))
                .findFirst();
        if (found.isPresent()) {
            products.remove(found.get());
            return found;
        } else {
            return Optional.empty();
        }
    }
}
