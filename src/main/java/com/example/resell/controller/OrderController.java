package com.example.resell.controller;

import com.example.resell.model.Order;
import com.example.resell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/demo")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * Returns an order by id.
     * @param id
     * @return DataResponse(status, message, order).
     */
    @GetMapping("/order/id")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    /**
     * Returns a list of all orders from DB.
     * @return DataResponse(status, message, list of orders).
     */
    @GetMapping("/order/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    /**
     * Creates an order and saves it in the DB.
     * @param order
     * @return DataResponse (status, message).
     */
    @PostMapping("/order/add")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.addOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    /**
     * Updates an existing order from DB.
     * @param order
     * @return DataResponse (status, message).
     */
    @PutMapping("/order/update")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        order.setId(id);
        Order updatedOrder = orderService.updateOrder(order);
        return ResponseEntity.status(HttpStatus.OK).body(updatedOrder);
    }

    /**
     *  Deletes an order from DB.
     * @param id
     * @return DataResponse (status, message).
     */
    @DeleteMapping("/order/delete")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
