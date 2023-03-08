package com.example.restservice.service;

import com.example.restservice.exception.CustomerExistException;
import com.example.restservice.exception.CustomerNotFoundException;
import com.example.restservice.model.Customer;
import com.example.restservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.restservice.constants.ErrorMessages.CUSTOMER_EXIST_NAME;
import static com.example.restservice.constants.ErrorMessages.NO_CUSTOMER_FOUND_ID;

/**
 * @Author: Yeming Hu
 * @Date: 3/7/2023
 * @Description: com.example.restservice.service
 * @Version: 1.0
 */
@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    @Override
    public Customer getCustomerByID(Long customerID) {
        Customer customerFound = customerRepository.getCustomerByID(customerID);
        if(customerFound == null){
            throw new CustomerNotFoundException(String.format(NO_CUSTOMER_FOUND_ID,customerID));
        }
        return customerFound;
    }

    @Override
    public Customer getCustomerByName(String customerName) {
        Customer customerFound = customerRepository.getCustomerByName(customerName);
        if(customerFound == null){
            throw new CustomerNotFoundException(String.format(NO_CUSTOMER_FOUND_ID,customerName));
        }
        return customerFound;
    }

    @Override
    public Long addCustomer(Customer customer) {
        //validation to check we do not add a customer with the same name
        Customer customerFound = customerRepository.getCustomerByName(customer.getCustomerName());
        if(customerFound != null){
            throw new CustomerExistException(String.format(CUSTOMER_EXIST_NAME, customer.getCustomerName()));
        }
        return customerRepository.addCustomer(customer);
    }

    @Override
    public void editCustomer(Long customerID, Customer customer) {
        //validation
        //validateID
        Customer customerFoundByID = customerRepository.getCustomerByID(customerID);
        if(customerFoundByID == null){
            throw new CustomerNotFoundException(String.format(NO_CUSTOMER_FOUND_ID,customerID));
        }
        //validateName
        Customer customerFoundByName = customerRepository.getCustomerByName(customer.getCustomerName());
        if(customerFoundByName != null){
            throw new CustomerExistException(String.format(CUSTOMER_EXIST_NAME,customer.getCustomerName()));
        }
        customerRepository.editCustomer(customerID,customer);

    }

    @Override
    public void deleteCustomer(Long customerID) {
        //validateID
        Customer customerFoundByID = customerRepository.getCustomerByID(customerID);
        if(customerFoundByID == null) {
            throw new CustomerNotFoundException(String.format(NO_CUSTOMER_FOUND_ID,customerID));
        }
        customerRepository.deleteCustomer(customerID);

    }
}
