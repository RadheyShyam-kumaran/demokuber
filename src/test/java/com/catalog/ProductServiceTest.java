package com.catalog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.catalog.model.Product;
import com.catalog.service.ProductService;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void testGetAllProducts() {
        List<Product> products = productService.getAllProducts();
        assertNotNull(products);
        assertTrue(products.size() > 0);
        System.out.println("✅ Total products: " + products.size());
    }

    @Test
    void testAddProduct() {
        Product p = new Product();
        p.setName("Test Product");
        p.setCategory("Test");
        p.setDescription("Test Description");
        p.setPrice(99.99);
        p.setStock(10);

        Product saved = productService.addProduct(p);
        assertNotNull(saved.getId());
        assertEquals("Test Product", saved.getName());
        System.out.println("✅ Product added with ID: " + saved.getId());
    }

    @Test
    void testGetById() {
        Product p = new Product();
        p.setName("Find Me");
        p.setCategory("Test");
        p.setDescription("Find this product");
        p.setPrice(49.99);
        p.setStock(5);

        Product saved = productService.addProduct(p);
        Product found = productService.getById(saved.getId());

        assertNotNull(found);
        assertEquals("Find Me", found.getName());
        System.out.println("✅ Product found: " + found.getName());
    }

    @Test
    void testDeleteProduct() {
        Product p = new Product();
        p.setName("Delete Me");
        p.setCategory("Test");
        p.setDescription("Delete this");
        p.setPrice(9.99);
        p.setStock(1);

        Product saved = productService.addProduct(p);
        boolean deleted = productService.deleteProduct(saved.getId());

        assertTrue(deleted);
        assertNull(productService.getById(saved.getId()));
        System.out.println("✅ Product deleted successfully");
    }

    @Test
    void testGetByCategory() {
        List<Product> laptops = productService.getByCategory("Laptops");
        assertNotNull(laptops);
        assertTrue(laptops.size() > 0);
        System.out.println("✅ Laptops found: " + laptops.size());
    }
}