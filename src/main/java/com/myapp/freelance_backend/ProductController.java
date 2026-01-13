package com.myapp.freelance_backend;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @PostMapping("/products")
    public ResponseEntity<ApiResponse> registerProduct(@RequestBody Product product) {
        if(product.getName()== null || product.getName().isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse("error","Product name is required",null));
        }
        if(product.getCategory()== null || product.getCategory().isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse("error","Product category is required",null));
        }
        if(product.getPrice() <= 0) {
            return ResponseEntity.status(400).body(new ApiResponse("error","Product price should be above 0",null));
        }
        return ResponseEntity.status(201).body(new ApiResponse("success","Product created successfully",product));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable int id) {
        if(id==1) {
            Product product = new Product();
            product.setId(1);
            product.setName("Laptop");
            product.setPrice(50000);
            product.setCategory("Electronics");
            return ResponseEntity.status(200).body(new ApiResponse("success","Product found",product));
        }
        return ResponseEntity.status(404).body(new ApiResponse("error","Product not found",null));
    }

    @GetMapping("/products")
    public  ResponseEntity<ApiResponse> getProducts(@RequestParam(required = false) String category) {
        if(category != null) {
            return ResponseEntity.status(200).body(new ApiResponse("success","Showing products in category : "+category,null));
        }
        return ResponseEntity.status(200).body(new ApiResponse("success","Showing all products",null));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable int id, @RequestBody Product product) {

        if( product.getName() == null || product.getName().isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse("error","Product name required",null));
        }
        if(product.getCategory() == null || product.getCategory().isEmpty() ) {
            return ResponseEntity.status(400).body(new ApiResponse("error","Product category required",null));
        }
        if(product.getPrice() <= 0) {
            return ResponseEntity.status(400).body(new ApiResponse("error","Product price should be above 0",null));
        }
        if( id != 1) {
            return ResponseEntity.status(404).body(new ApiResponse("error","Product not found",null));
        }
        product.setId(id);
        return ResponseEntity.ok(new ApiResponse("success","Product updated successfully",product));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable int id) {
        if(id==1) {
            return ResponseEntity.status(200).body(new ApiResponse("success","Product deleted successfully",null));
        }
        return ResponseEntity.status(404).body(new ApiResponse("error","Product not found, cannot delete",null));
    }
}
