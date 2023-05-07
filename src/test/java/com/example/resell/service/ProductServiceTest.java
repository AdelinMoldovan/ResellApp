package com.example.resell.service;


import com.example.resell.exception.ProductNotFoundException;
import com.example.resell.model.Product;
import com.example.resell.repository.ProductRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.then;
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private AdminService adminService;
    private ProductServiceImpl productService;
    private Product product;


    @Before
    public void setUp() {
        initMocks(this);
        productService = new ProductServiceImpl(productRepository, adminService);
        product = new Product();
    }

    @Test
    public void givenExistingProductId_whenFindById_thenFindProduct(){
        product.setId(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product foundProduct = productService.findById(product.getId());

        assertNotNull(foundProduct);
        assertEquals(1L, foundProduct.getId());
    }

    @Test
    public void givenNonExistingProductId_whenFindById_thenFindProduct() {
        product.setId(400L);
        when(productRepository.findById(400L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> {
            productService.findById(product.getId());
        });
    }

    @Test
    public void givenName_whenFindAllByName_thenFindProducts(){
        product.setId(1L);
        product.setName("jordan 4 a ma maniere");
//        Product product1 = new Product();
//
//        product1.setName("jordan 4 university blue");

        when(productRepository.findAllByName("jordan 4 a ma maniere")).thenReturn(List.of(product));
        List<Product> products = productService.findAllByName("jordan 4 a ma maniere");

        assertNotNull(products);
        assertEquals(1, products.size());
        assertTrue(products.contains(product));
    }

    @Test
    public void givenCategory_whenFindAllByCategory_thenFindProducts(){
        product.setId(1L);
        product.setCategory("nike");
        //Product product1 = new Product();

        //product1.setCategory("addidas");

        when(productRepository.findAllByCategory("nike")).thenReturn(List.of(product));
        List<Product> products = productService.findAllByCategory("nike");

        assertNotNull(products);
        assertEquals(1, products.size());
        assertTrue(products.contains(product));
    }

    @Test
    public void givenValidProduct_whenSaveProduct_thenReturnSavedProduct() {
        product.setId(1L);
        product.setName("air force");
        product.setCategory("nike");
        product.setManufacturer("NIKE");
        product.setPrice(500);
        product.setStock("10");
        product.setDescription("Culoare alb, confectionat din piele");

        when(productRepository.save(product)).thenReturn(product);
        Product savedProduct = productService.addProduct(product);

        assertNotNull(savedProduct);
        assertEquals("air force", savedProduct.getName());
    }

    @Test
    public void givenExistingProduct_whenUpdateProduct_thenReturnUpdatedProduct(){
        product.setId(1L);
        product.setName("air force");
        product.setCategory("nike");
        product.setManufacturer("NIKE");
        product.setPrice(500);
        product.setStock("10");
        product.setDescription("Culoare alb, confectionat din piele");

        Product productUpdate = new Product();

        productUpdate.setId(1L);
        productUpdate.setName("blazer");

        Product productToReturn = new Product();
        productToReturn.setId(product.getId());
        productToReturn.setName(product.getName());
        productToReturn.setCategory(product.getCategory());
        productToReturn.setManufacturer(product.getManufacturer());
        productToReturn.setPrice(product.getPrice());
        productToReturn.setStock(product.getStock());
        productToReturn.setDescription(product.getDescription());

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(productUpdate)).thenReturn(productToReturn);

        Product savedProduct = productService.updateProduct(productUpdate);

        assertNotNull(savedProduct);
        assertEquals("blazer", savedProduct.getName());
    }

    @Test
    public void givenNonExistingProduct_whenUpdateProduct_thenThrowException(){
        Product productUpdate = new Product();
        productUpdate.setId(400L);
        productUpdate.setName("blazer");

        when(productRepository.findById(400L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, ()-> {
            productService.updateProduct(productUpdate);
        });
    }

    @Test
    public void givenExistingProduct_whenDeleteProduct_thenSuccess() {
        product.setId(1L);
        product.setName("air force");
        product.setCategory("nike");
        product.setManufacturer("NIKE");
        product.setPrice(500);
        product.setStock("10");
        product.setDescription("Culoare alb, confectionat din piele");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        doNothing().when(productRepository).deleteById(product.getId());

        productService.deleteById(product.getId());
        then(productRepository).should().deleteById(1L);
    }

    @Test
    public void givenNonExistingProduct_whenDeleteProduct_thenThrowException(){
        product.setId(1L);
        product.setName("air force");
        product.setCategory("nike");
        product.setManufacturer("NIKE");
        product.setPrice(500);
        product.setStock("10");
        product.setDescription("Culoare alb, confectionat din piele");

        when(productRepository.findById(400L)).thenReturn(Optional.empty());
        doNothing().when(productRepository).deleteById(product.getId());

        assertThrows(ProductNotFoundException.class, ()->{
           productService.deleteById(product.getId());
        });
    }

}
