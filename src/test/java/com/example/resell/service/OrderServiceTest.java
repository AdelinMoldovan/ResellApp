package com.example.resell.service;


import com.example.resell.exception.CustomerNotFoundException;
import com.example.resell.exception.InvalidOrderException;
import com.example.resell.exception.OrderNotFoundException;
import com.example.resell.model.*;
import com.example.resell.repository.OrderRepository;
import org.checkerframework.checker.nullness.Opt;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerService customerService;

    @Mock
    private ProductService productService;

    private OrderService orderService;
    private Order order;


    @Before
    public void setUp(){
        initMocks(this);
        orderService = new OrderServiceImpl(orderRepository, customerService, productService);
        order = new Order();
    }

    @Test
    public void givenExistingOrderId_whenFindById_thenFindOrder(){
        order.setId(1L);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        Order foundOrder = orderService.findById(order.getId());

        assertNotNull(foundOrder);
        assertEquals(1L, foundOrder.getId());
    }

    @Test
    public void givenNonExistingOrderId_whenFindById_thenThrowException(){
        order.setId(400L);

        when(orderRepository.findById(400L)).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> {
            orderService.findById(order.getId());
        });
    }

    @Test
    public void givenValidOrder_whenSaveOrder_thenReturnSavedOrder() throws InvalidOrderException {
        Customer customer = new Customer();
        customer.setId(1l);
        customer.setFirstName("Adelin");
        customer.setLastName("Moldovan");
        customer.setEmail("moldovanadelin111@gmail.com");
        customer.setPassword("password");
        customer.setCustomerPhone("0755538466");

        ShoppingCart shoppingCart = new ShoppingCart();
        ShippingAddress addr = new ShippingAddress();
        addr.setAddress("Baritiu 69");
        addr.setCity("Cluj");
        addr.setZipcode("12345");
        addr.setCountry("Ungaria");

        Order order = new Order();
        order.setId(1L);
        order.setShoppingCart(shoppingCart);
        order.setCustomer(customer);
        order.setShippingAddress(addr);
        order.setTime(LocalDateTime.now());
        //orderService.addOrder(order);

        when(orderRepository.save(order)).thenReturn(order);
        Order savedOrder = orderService.addOrder(order);

        assertNotNull(savedOrder);
        assertEquals(1L, order.getId());
    }

//    @Test
//    public void givenInvalidCustomer_whenSaveOrder_thenThrowException() throws InvalidOrderException {
//        Customer customer = new Customer();
//        customer.setId(1l);
//        customer.setFirstName("Adelin");
//        customer.setLastName("Moldovan");
//        customer.setEmail("moldovanadelin111@gmail.com");
//        customer.setPassword("password");
//        customer.setCustomerPhone("0755538466");
//
//        ShoppingCart shoppingCart = new ShoppingCart();
//        Product product1 = new Product();
//        product1.setName("Nike AirForce 1");
//        product1.setCategory("Sneakers");
//        product1.setDescription("Culoare alb, marimea 41");
//        product1.setManufacturer("Nike");
//        product1.setPrice(650);
//        product1.setStock("5");
//
//
//        SingleCartItem item1 = new SingleCartItem();
//        item1.setProduct(product1);
//        item1.setQuantity(1);
//
//        ShoppingCart cart = new ShoppingCart();
//        cart.setCustomer(customer);
//        cart.addItem(item1);
//
//        ShippingAddress addr = new ShippingAddress();
//        addr.setAddress("Baritiu 69");
//        addr.setCity("Cluj");
//        addr.setZipcode("12345");
//        addr.setCountry("Ungaria");
//
//        Order order = new Order();
//        order.setId(1L);
//        order.setShoppingCart(shoppingCart);
//        order.setCustomer(customer);
//        order.setShippingAddress(addr);
//        order.setTime(LocalDateTime.now());
//
//        willThrow(new CustomerNotFoundException()).given(customerService).findById(customer.getId());
//
//        assertThrows(InvalidOrderException.class, () -> {
//            orderService.addOrder(order);
//        });
//
//    }

    @Test
    public void givenExistingOrder_whenUpdateOrder_thenReturnSavedOrder(){

        Customer customer = new Customer();
        customer.setId(1l);
        customer.setFirstName("Adelin");
        customer.setLastName("Moldovan");
        customer.setEmail("moldovanadelin111@gmail.com");
        customer.setPassword("password");
        customer.setCustomerPhone("0755538466");

        ShoppingCart shoppingCart = new ShoppingCart();
        Product product1 = new Product();
        product1.setName("Nike AirForce 1");
        product1.setCategory("Sneakers");
        product1.setDescription("Culoare alb, marimea 41");
        product1.setManufacturer("Nike");
        product1.setPrice(650);
        product1.setStock("5");


       /* SingleCartItem item1 = new SingleCartItem();
        item1.setProduct(product1);
        item1.setQuantity(1);

        ShoppingCart cart = new ShoppingCart();
        cart.setCustomer(customer);
        cart.addItem(item1);*/

        ShippingAddress addr = new ShippingAddress();
        addr.setAddress("Baritiu 69");
        addr.setCity("Cluj");
        addr.setZipcode("12345");
        addr.setCountry("Ungaria");

        Order order = new Order();
        order.setShoppingCart(shoppingCart);
        order.setCustomer(customer);
        order.setShippingAddress(addr);
        order.setTime(LocalDateTime.of(2023, 5, 7, 14, 20, 00));
        order.setId(1L);

        Order updateOrder = new Order();
        updateOrder.setId(1L);
        updateOrder.setTime(LocalDateTime.of(2023,5,7,14,20,00));

        Order orderToReturn = new Order(1L, shoppingCart, customer, addr,LocalDateTime.of(2023,5,7,14,20,00));

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepository.save(order)).thenReturn(orderToReturn);


        Order updatedOrder = orderService.updateOrder(updateOrder);

        assertNotNull(updatedOrder);
        assertEquals(addr, updatedOrder.getShippingAddress());
        assertEquals(LocalDateTime.of(2023, 5, 7, 14, 20, 00),updatedOrder.getTime());
    }

    @Test
    public void givenNonExistingOrder_whenUpdateOrder_thenThrowsException(){
        order.setId(400L);

        when(orderRepository.findById(400L)).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> {
            orderService.updateOrder(order);
        });
    }

    @Test
    public void givenExistingOrder_whenDeleteOrder_thenSuccess(){
        Customer customer = new Customer();
        customer.setId(1l);
        customer.setFirstName("Adelin");
        customer.setLastName("Moldovan");
        customer.setEmail("moldovanadelin111@gmail.com");
        customer.setPassword("password");
        customer.setCustomerPhone("0755538466");

        ShoppingCart shoppingCart = new ShoppingCart();
        Product product1 = new Product();
        product1.setName("Nike AirForce 1");
        product1.setCategory("Sneakers");
        product1.setDescription("Culoare alb, marimea 41");
        product1.setManufacturer("Nike");
        product1.setPrice(650);
        product1.setStock("5");

        /*
        SingleCartItem item1 = new SingleCartItem();
        item1.setProduct(product1);
        item1.setQuantity(1);

        ShoppingCart cart = new ShoppingCart();
        cart.setCustomer(customer);
        cart.addItem(item1);
        */
        ShippingAddress addr = new ShippingAddress();
        addr.setAddress("Baritiu 69");
        addr.setCity("Cluj");
        addr.setZipcode("12345");
        addr.setCountry("Ungaria");

        Order order = new Order();
        order.setShoppingCart(shoppingCart);
        order.setCustomer(customer);
        order.setShippingAddress(addr);
        order.setTime(LocalDateTime.of(2023, 5, 7, 14, 20, 00));
        order.setId(1L);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        doNothing().when(orderRepository).deleteById(order.getId());

        orderService.deleteById(order.getId());
        then(orderRepository).should().deleteById(1L);
    }

    @Test
    public void givenNonExistingOrder_whenDeleteOrder_thenThrowException() {
        order.setId(400L);

        when(orderRepository.findById(400L)).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> {
            orderService.deleteById(order.getId());
        });
    }

}
