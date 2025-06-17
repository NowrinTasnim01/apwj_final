package com.example.Shop_management.api;

import com.example.Shop_management.entity.Product;
import com.example.Shop_management.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shop")
@CrossOrigin
public class Api {
    private ProductService ps;


    public Api(ProductService productService) {
        this.ps = productService;
    }

    @PostMapping("/products")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        ps.addProduct(product);
        return ResponseEntity.ok("Product added");
    }

    @PostMapping("/products/bulk")
    public ResponseEntity<String> addProductsBulk(@RequestBody List<Product> products) {
        ps.addProductsBulk(products);
        return ResponseEntity.ok("Bulk products added");
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody Product product) {
        product.setId(id);
        ps.updateProduct(product);
        return ResponseEntity.ok("Product updated");
    }

    @GetMapping("/products/expiring")
    public ResponseEntity<List<Product>> getExpiringProducts() {
        return ResponseEntity.ok(ps.getExpiringProducts());
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(ps.getAllProducts());
    }

    @GetMapping("/reports/value")
    public ResponseEntity<Map<?, ?>> getTotalValueByCategory() {
        return ResponseEntity.ok(ps.getTotalValueByCategory());
    }

    @GetMapping("/reports/discounts")
    public ResponseEntity<Map<?, ?>> getProductsByCategoryWithDiscounts() {
        return ResponseEntity.ok(ps.getProductsByCategoryWithDiscounts());
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> remove(@PathVariable int id) {
        ps.delete(id);
        return ResponseEntity.ok("Product deleted.");
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = ps.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }


}
