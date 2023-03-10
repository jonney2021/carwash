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
    /**
     *
     * @return the list of orders
     */
    List<Order> getAllOrders();

    /**
     *
     * @param orderID  the id of the order
     * @return
     */
    Order getOrderByID(Long orderID);

    /**
     *
     * @param order
     * @return the number of the order
     */
    Long addOrder(Order order);

    /**
     *
     * @param orderID the id of the order
     * @param order  an order with that id
     */
    void editOrder(Long orderID,Order order);

    /**
     *
     * @param orderID the id of that order
     */
    void deleteOrder(Long orderID);

}
