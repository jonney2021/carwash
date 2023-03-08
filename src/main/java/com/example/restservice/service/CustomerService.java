package com.example.restservice.service;

import com.example.restservice.model.Customer;

import java.util.List;

/**
 * @Author: Yeming Hu
 * @Date: 3/7/2023
 * @Description: com.example.restservice.service
 * @Version: 1.0
 */
public interface CustomerService {

    /**
     *
     * @return the list of customers
     */
    List<Customer> getAllCustomers();

    /**
     *
     * @param customerID the id of the customer
     * @return a customer with that id
     */
    Customer getCustomerByID(Long customerID);

    /**
     *
     * @param customerName the name of the customer
     * @return a customer with that name
     */
    Customer getCustomerByName(String customerName);

    /**
     *
     * @param customer
     * @return the number of customers
     */
    Long addCustomer(Customer customer);

    /**
     *
     * @param customerID the id of customer
     * @param customer a customer with that id
     */
    void editCustomer(Long customerID, Customer customer);

    /**
     *
     * @param customerID the id of customer
     */
    void deleteCustomer(Long customerID);

}
