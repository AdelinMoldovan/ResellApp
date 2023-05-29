package com.example.resell.controller;


import com.example.resell.model.Product;
import com.example.resell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Returns a product by id.
     * @param id
     * @return DataResponse(status, message, customer).
     */
    @GetMapping("/product/id")
    public ResponseEntity findProductById(@RequestParam long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findById(id));
    }

    /**
     * Returns a product by name.
     * @param name
     * @return DataResponse(status, message, customer).
     */
    @GetMapping("/product/name")
    public ResponseEntity findAllProductsByName(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAllByName(name));
    }

    /**
     * Returns a product by category.
     * @param category
     * @return DataResponse(status, message, customer).
     */
    @GetMapping("/product/category")
    public ResponseEntity findAllProductsByType(@RequestParam String category) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAllByCategory(category));
    }

    /**
     * Returns a list of all products from DB.
     * @return DataResponse(status, message, list of products).
     */
    @GetMapping("/product/all")
    public ResponseEntity findAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    /**
     * Creates a product and saves it in the DB.
     * @param product
     * @return DataResponse (status, message).
     */
    @PostMapping("/product/add")
    public ResponseEntity addProduct(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.addProduct(product));
    }

    /**
     * Updates an existing product from DB.
     * @param product
     * @return DataResponse (status, message).
     */
    @PutMapping("/product/update")
    public ResponseEntity updateProduct(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(product));
    }

    /**
     *  Deletes a product from DB.
     * @param id
     * @return DataResponse (status, message).
     */
    @DeleteMapping("/product/delete")
    public void deleteProduct(@RequestParam long id) {
        productService.deleteById(id);
    }

}
