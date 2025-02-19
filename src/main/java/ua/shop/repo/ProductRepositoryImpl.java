package ua.shop.repo;

import lombok.Data;
import lombok.Getter;
import ua.shop.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Getter
@Data
public class ProductRepositoryImpl implements ProductRepo {
    private List<Product> products;

    public ProductRepositoryImpl() {
        products = new ArrayList<>();

        Product product1 = new Product(1L, "Laptop", 999.99);
        Product product2 = new Product(2L, "Smartphone", 699.99);
        Product product3 = new Product(3L, "Headphones", 199.99);

        products.add(product1);
        products.add(product2);
        products.add(product3);
    }

    @Override
    public Product addProduct(Product product) {
        if (product.getId() != null) {
            throw new IllegalArgumentException("Product id already exists");
        }
        Long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        product.setId(id);
        products.add(product);
        return product;
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<Product> updateProduct(Product product) {
        if (product != null && product.getId() != null) {
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
    public Optional<Product> deleteProduct(Long id) {
        Optional<Product> found = products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        if (found.isPresent()) {
            products.remove(found.get());
            return found;
        } else {
            return Optional.empty();
        }
    }
}
