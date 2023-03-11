package com.example.restservice.api;

import com.example.restservice.exception.CustomerNotFoundException;
import com.example.restservice.exception.OrderNotFoundException;
import com.example.restservice.exception.ProductNotFoundException;
import com.example.restservice.model.Order;
import com.example.restservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Yeming Hu
 * @Date: 3/8/2023
 * @Description: com.example.restservice.api
 * @Version: 1.0
 */
@RestController
@RequestMapping("/carwash")
@CrossOrigin(maxAge = 45000)
public class OrderController {
    @Autowired
    private OrderService orderService;

    //get all orders
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders(){
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    //get order by id
    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderByID(@PathVariable("id") Long orderID){
        try{
            return new ResponseEntity<>(orderService.getOrderByID(orderID),HttpStatus.OK);
        }catch(OrderNotFoundException ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/orders")
    public ResponseEntity<Long> addOrder(@RequestBody Order order){
        try{
            return new ResponseEntity<>(orderService.addOrder(order),HttpStatus.CREATED);
        }catch(CustomerNotFoundException | ProductNotFoundException ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Void> editOrder(@PathVariable("id") Long orderID, @RequestBody Order order){
        try{
            orderService.editOrder(orderID,order);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(OrderNotFoundException | CustomerNotFoundException | ProductNotFoundException ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long orderID){
        try{
            orderService.deleteOrder(orderID);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(OrderNotFoundException ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
