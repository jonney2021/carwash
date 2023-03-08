package com.example.restservice.service;

import com.example.restservice.exception.CustomerNotFoundException;
import com.example.restservice.exception.OrderNotFoundException;
import com.example.restservice.exception.ProductNotFoundException;
import com.example.restservice.model.Customer;
import com.example.restservice.model.Order;
import com.example.restservice.model.Product;
import com.example.restservice.repository.CustomerRepository;
import com.example.restservice.repository.OrderRepository;
import com.example.restservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.restservice.constants.ErrorMessages.*;

/**
 * @Author: Yeming Hu
 * @Date: 3/8/2023
 * @Description: com.example.restservice.service
 * @Version: 1.0
 */
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    @Override
    public Order getOrderByID(Long orderID) {
        Order orderFound = orderRepository.getOrderByID(orderID);
        if(orderFound == null){
            throw new OrderNotFoundException(String.format(NO_ORDER_FOUND_ID,orderID));
        }
        return orderFound;
    }

    @Override
    public Long addOrder(Order order) {
        Customer customer = customerRepository.getCustomerByID(order.getCustomerID());
        if(customer == null){
            throw new CustomerNotFoundException(String.format(NO_CUSTOMER_FOUND_ID,order.getCustomerID()));
        }
        Product product = productRepository.getProductByID(order.getProductID());
        if(product == null){
            throw new ProductNotFoundException(String.format(NO_PRODUCT_FOUND_ID,order.getProductID()));
        }
        return orderRepository.addOrder(order);
    }

    @Override
    public void editOrder(Long orderID, Order order) {
        Order orderFound = orderRepository.getOrderByID(orderID);
        if(orderFound == null){
            throw new OrderNotFoundException(String.format(NO_ORDER_FOUND_ID,orderID));
        }
        Customer customer = customerRepository.getCustomerByID(order.getCustomerID());
        if(customer == null){
            throw new CustomerNotFoundException(String.format(NO_CUSTOMER_FOUND_ID,order.getCustomerID()));
        }
        Product product = productRepository.getProductByID(order.getProductID());
        if(product == null){
            throw new ProductNotFoundException(String.format(NO_PRODUCT_FOUND_ID,order.getProductID()));
        }

        orderRepository.editOrder(orderID,order);
    }

    @Override
    public void deleteOrder(Long orderID) {
        //validateID
        Order orderFound = orderRepository.getOrderByID(orderID);
        if(orderFound == null){
            throw new OrderNotFoundException(String.format(NO_ORDER_FOUND_ID,orderID));
        }
        orderRepository.deleteOrder(orderID);
    }
}
