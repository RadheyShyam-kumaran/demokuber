package com.catalog.controller;

import com.catalog.model.Product;
import com.catalog.model.PodInfo;
import com.catalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Injected from Kubernetes deployment env variables
    @Value("${POD_NAME:local-machine}")
    private String podName;

    @Value("${APP_VERSION:1.0}")
    private String appVersion;

    // GET all products
    @GetMapping
    public List<Product> getAll() {
        return productService.getAllProducts();
    }

    // GET product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        Product p = productService.getById(id);
        if (p == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(p);
    }

    // GET by category
    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@PathVariable String category) {
        return productService.getByCategory(category);
    }

    // GET all categories
    @GetMapping("/categories")
    public List<String> getCategories() {
        return productService.getCategories();
    }

    // POST add product
    @PostMapping
    public Product add(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    // PUT update product
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        Product updated = productService.updateProduct(id, product);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    // DELETE product
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (!productService.deleteProduct(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Deleted successfully!");
    }

    // Pod info endpoint — shows which pod is serving the request
    @GetMapping("/info")
    public PodInfo getInfo() {
        return new PodInfo(
            podName,
            appVersion,
            "Running",
            "Served by Kubernetes Pod on Azure AKS"
        );
    }

    // Health check
    @GetMapping("/health")
    public String health() {
        return "OK - Running on Pod: " + podName + " | Version: " + appVersion;
    }
}
