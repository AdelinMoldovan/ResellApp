package com.example.resell.controller;


import com.example.resell.model.Person;
import com.example.resell.model.Product;
import com.example.resell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

//    @Autowired
//    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {

        this.productService = productService;
    }
    @RequestMapping(value = "/getAllProducts", method = RequestMethod.GET)
    public ModelAndView getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return new ModelAndView("productsList", "products", products);
    }

    @RequestMapping(value = "/getProductById/{productId}", method = RequestMethod.GET)
    public ModelAndView getProductById(@PathVariable(value = "productId") int productId){
        Product product = productService.getProductById(productId);
        return new ModelAndView("productPage", "product", product);

    }

    @DeleteMapping(path = "{productId}") //delete
    public void deletePerson(@PathVariable("productId") int productId) {
        productService.deleteProduct(productId);
    }

    @PostMapping //add new product
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @PutMapping(path = "{productId}") //update
    public void updateProduct(
            @PathVariable("productId") int productId,
            @RequestParam(required = false) double price,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String producer,
            @RequestParam(required = false) String category) {
        productService.updateProduct(productId, price, name, description, producer, category);
    }
}
