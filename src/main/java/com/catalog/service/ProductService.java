package com.catalog.service;

import com.catalog.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    private Map<Long, Product> store = new LinkedHashMap<>();
    private Long counter = 1L;

    public ProductService() {
        addProduct(new Product(null, "MacBook Pro", "Laptops",
                "Apple M3 chip, 16GB RAM, 512GB SSD", 1999.99, 10));
        addProduct(new Product(null, "iPhone 15", "Phones",
                "6.1 inch display, 128GB, A16 Bionic chip", 899.99, 25));
        addProduct(new Product(null, "Sony WH-1000XM5", "Audio",
                "Industry leading noise cancellation headphones", 349.99, 40));
        addProduct(new Product(null, "Samsung 4K Monitor", "Monitors",
                "27 inch 4K UHD display, 144Hz refresh rate", 499.99, 15));
        addProduct(new Product(null, "iPad Air", "Tablets",
                "10.9 inch Liquid Retina display, M1 chip", 749.99, 20));
        addProduct(new Product(null, "Logitech MX Keys", "Accessories",
                "Advanced wireless illuminated keyboard", 109.99, 60));
        addProduct(new Product(null, "Dell XPS 15", "Laptops",
                "Intel i9, 32GB RAM, OLED display", 1799.99, 8));
        addProduct(new Product(null, "Samsung Galaxy S24", "Phones",
                "6.2 inch Dynamic AMOLED, 256GB", 799.99, 30));
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(store.values());
    }

    public List<Product> getByCategory(String category) {
        List<Product> result = new ArrayList<>();
        for (Product p : store.values()) {
            if (p.getCategory().equalsIgnoreCase(category)) result.add(p);
        }
        return result;
    }

    public List<String> getCategories() {
        Set<String> cats = new LinkedHashSet<>();
        for (Product p : store.values()) cats.add(p.getCategory());
        return new ArrayList<>(cats);
    }

    public Product getById(Long id) { return store.get(id); }

    public Product addProduct(Product p) {
        p.setId(counter++);
        store.put(p.getId(), p);
        return p;
    }

    public Product updateProduct(Long id, Product p) {
        if (!store.containsKey(id)) return null;
        p.setId(id);
        store.put(id, p);
        return p;
    }

    public boolean deleteProduct(Long id) {
        if (!store.containsKey(id)) return false;
        store.remove(id);
        return true;
    }
}
