package com.catalog.controller;

import com.catalog.model.Category;
import com.catalog.model.PodInfo;
import com.catalog.model.Product;
import com.catalog.repository.CategoryRepository;
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

    @Autowired
    private CategoryRepository categoryRepository;

    @Value("${POD_NAME:local-machine}")
    private String podName;

    @Value("${APP_VERSION:1.0}")
    private String appVersion;

    @GetMapping
    public List<Product> getAll() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        Product p = productService.getById(id);
        if (p == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(p);
    }

    @GetMapping("/category/{categoryId}")
    public List<Product> getByCategory(@PathVariable Long categoryId) {
        Category cat = categoryRepository.findById(categoryId).orElse(null);
        if (cat == null) return List.of();
        return productService.getByCategory(cat);
    }

    @GetMapping("/search")
    public List<Product> search(@RequestParam String name) {
        return productService.searchByName(name);
    }

    @PostMapping
    public Product add(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        Product updated = productService.updateProduct(id, product);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (!productService.deleteProduct(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Deleted successfully!");
    }

    @GetMapping("/info")
    public PodInfo getInfo() {
        return new PodInfo(podName, appVersion, "Running",
                "Served by Kubernetes Pod on Azure AKS with MySQL");
    }

    @GetMapping("/health")
    public String health() {
        return "OK - Pod: " + podName + " | Version: " + appVersion + " | DB: MySQL";
    }
}
