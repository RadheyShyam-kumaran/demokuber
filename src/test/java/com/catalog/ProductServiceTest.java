package com.catalog;

import com.catalog.model.Category;
import com.catalog.model.Product;
import com.catalog.service.CategoryService;
import com.catalog.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Test
    void testGetAllProducts() {
        List<Product> products = productService.getAllProducts();
        assertNotNull(products);
        assertTrue(products.size() > 0);
        System.out.println("✅ Total products: " + products.size());
    }

    @Test
    void testAddProduct() {
        Category cat = categoryService.getAllCategories().get(0);
        Product p = new Product();
        p.setName("Test Product");
        p.setDescription("Test Description");
        p.setPrice(99.99);
        p.setStock(10);
        p.setCategory(cat);

        Product saved = productService.addProduct(p);
        assertNotNull(saved.getId());
        assertEquals("Test Product", saved.getName());
        System.out.println("✅ Product added with ID: " + saved.getId());
    }

    @Test
    void testGetById() {
        List<Product> products = productService.getAllProducts();
        assertFalse(products.isEmpty());
        Product first = products.get(0);
        Product found = productService.getById(first.getId());
        assertNotNull(found);
        System.out.println("✅ Product found: " + found.getName());
    }

    @Test
    void testDeleteProduct() {
        Category cat = categoryService.getAllCategories().get(0);
        Product p = new Product();
        p.setName("Delete Me");
        p.setDescription("Delete this");
        p.setPrice(9.99);
        p.setStock(1);
        p.setCategory(cat);

        Product saved = productService.addProduct(p);
        boolean deleted = productService.deleteProduct(saved.getId());
        assertTrue(deleted);
        assertNull(productService.getById(saved.getId()));
        System.out.println("✅ Product deleted successfully");
    }

    @Test
    void testGetAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        assertNotNull(categories);
        assertTrue(categories.size() > 0);
        System.out.println("✅ Total categories: " + categories.size());
    }
}
