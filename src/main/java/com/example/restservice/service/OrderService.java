package com.example.restservice.service;

import com.example.restservice.model.Order;

import java.util.List;

/**
 * @Author: Yeming Hu
 * @Date: 3/8/2023
 * @Description: com.example.restservice.service
 * @Version: 1.0
 */
public interface OrderService {
    List<Order> getAllOrders();

    Order getOrderByID(Long orderID);

    Long addOrder(Order order);

    void editOrder(Long orderID,Order order);

    void deleteOrder(Long orderID);


}
