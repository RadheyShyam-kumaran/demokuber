package com.catalog.service;

import com.catalog.model.Category;
import com.catalog.model.Product;
import com.catalog.repository.CategoryRepository;
import com.catalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) {
        if (categoryRepository.count() == 0) {

            Category laptops = categoryRepository.save(new Category("Laptops", "Portable computers"));
            Category phones = categoryRepository.save(new Category("Phones", "Mobile devices"));
            Category audio = categoryRepository.save(new Category("Audio", "Sound equipment"));
            Category monitors = categoryRepository.save(new Category("Monitors", "Display screens"));
            Category accessories = categoryRepository.save(new Category("Accessories", "Computer accessories"));

            Product p1 = new Product();
            p1.setName("MacBook Pro"); p1.setDescription("Apple M3 chip, 16GB RAM, 512GB SSD");
            p1.setPrice(1999.99); p1.setStock(10); p1.setCategory(laptops);
            productRepository.save(p1);

            Product p2 = new Product();
            p2.setName("Dell XPS 15"); p2.setDescription("Intel i9, 32GB RAM, OLED display");
            p2.setPrice(1799.99); p2.setStock(8); p2.setCategory(laptops);
            productRepository.save(p2);

            Product p3 = new Product();
            p3.setName("iPhone 15"); p3.setDescription("6.1 inch display, 128GB, A16 Bionic");
            p3.setPrice(899.99); p3.setStock(25); p3.setCategory(phones);
            productRepository.save(p3);

            Product p4 = new Product();
            p4.setName("Samsung Galaxy S24"); p4.setDescription("6.2 inch AMOLED, 256GB");
            p4.setPrice(799.99); p4.setStock(30); p4.setCategory(phones);
            productRepository.save(p4);

            Product p5 = new Product();
            p5.setName("Sony WH-1000XM5"); p5.setDescription("Industry leading noise cancellation");
            p5.setPrice(349.99); p5.setStock(40); p5.setCategory(audio);
            productRepository.save(p5);

            Product p6 = new Product();
            p6.setName("Samsung 4K Monitor"); p6.setDescription("27 inch 4K UHD display, 144Hz");
            p6.setPrice(499.99); p6.setStock(15); p6.setCategory(monitors);
            productRepository.save(p6);

            Product p7 = new Product();
            p7.setName("Logitech MX Keys"); p7.setDescription("Advanced wireless illuminated keyboard");
            p7.setPrice(109.99); p7.setStock(60); p7.setCategory(accessories);
            productRepository.save(p7);

            Product p8 = new Product();
            p8.setName("iPad Air"); p8.setDescription("10.9 inch Liquid Retina, M1 chip");
            p8.setPrice(749.99); p8.setStock(20); p8.setCategory(accessories);
            productRepository.save(p8);
        }
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> searchByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        if (!productRepository.existsById(id)) return null;
        product.setId(id);
        return productRepository.save(product);
    }

    public boolean deleteProduct(Long id) {
        if (!productRepository.existsById(id)) return false;
        productRepository.deleteById(id);
        return true;
    }
}
