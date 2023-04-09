package com.example.resell.controller;


import com.example.resell.model.Person;
import com.example.resell.model.Product;
import com.example.resell.service.ProductService;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/findById")
    public ResponseEntity findProductById(@RequestParam long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findById(id));
    }

    @GetMapping("/findByName")
    public ResponseEntity findAllProductsByName(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAllByName(name));
    }

    @GetMapping("/findByType")
    public ResponseEntity findAllProductsByType(@RequestParam String category) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAllByCategory(category));
    }

    @GetMapping()
    public ResponseEntity findAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.addProduct(product));
    }

    @PutMapping("/update")
    public ResponseEntity updateProduct(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(product));
    }

    @DeleteMapping("/delete")
    public void deleteProduct(@RequestParam long id) {
        productService.deleteById(id);
    }

}
