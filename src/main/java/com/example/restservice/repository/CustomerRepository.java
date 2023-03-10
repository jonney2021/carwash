package com.example.restservice.repository;

import com.example.restservice.model.Customer;
import com.example.restservice.model.Order;
import com.example.restservice.repository.entity.CustomerEntity;
import com.example.restservice.repository.entity.OrderEntity;
import com.example.restservice.repository.mapper.CustomerOrderRowMapper;
import com.example.restservice.repository.mapper.CustomerRowMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Yeming Hu
 * @Date: 3/7/2023
 * @Description: com.example.restservice.repository
 * @Version: 1.0
 */
@Repository
public class CustomerRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ModelMapper modelMapper;

    public List<Customer> getAllCustomers() {
        List<CustomerEntity> customerEntityList = jdbcTemplate.query("SELECT * FROM CUSTOMER", new CustomerRowMapper());
        List<Customer> customerResult = new ArrayList<>();
        for (CustomerEntity customerEntity : customerEntityList) {
            Customer customer = modelMapper.map(customerEntity, Customer.class);
            customerResult.add(customer);
        }
        return customerResult;
    }

    public Customer getCustomerByID(Long customerID) {
        String sql = "SELECT * FROM CUSTOMER C LEFT JOIN `ORDER` O ON C.customer_id = O.customer_id WHERE C.customer_id=?";
        try {
            CustomerEntity customerEntity = jdbcTemplate.queryForObject(sql, new CustomerOrderRowMapper(), customerID);
            Customer customer = modelMapper.map(customerEntity, Customer.class);
            return customer;
        } catch (EmptyResultDataAccessException exception) {
            return null;
        }
    }

    public Customer getCustomerByName(String customerName) {
        String sql = "SELECT * FROM CUSTOMER C LEFT JOIN `ORDER` O ON C.customer_id = O.customer_id WHERE C.customer_name=?";
        try {
            CustomerEntity customerEntity = jdbcTemplate.queryForObject(sql, new CustomerOrderRowMapper(), customerName);
            return modelMapper.map(customerEntity, Customer.class);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }

    public Long addCustomer(Customer customer) {
        String sql = "";
        sql = "INSERT INTO CUSTOMER(customer_name,customer_phone,car_make,car_model,car_plate_no) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, customer.getCustomerName(), customer.getCustomerPhone(), customer.getCarMake(), customer.getCarModel(), customer.getCarPlateNo());
        Long customerID = jdbcTemplate.queryForObject("SELECT MAX(customer_id) FROM CUSTOMER", Long.class);

        for(Order order : customer.getOrders()){
            sql = "INSERT INTO `order`(amount,order_date,customer_id,product_id) VALUES(?,CURRENT_TIMESTAMP,?,?)";
            jdbcTemplate.update(sql,order.getAmount(),customerID,order.getProductID());

        }
        return customerID;
    }

    public void editCustomer(Long customerID, Customer customer) {
        String sql = "UPDATE CUSTOMER SET customer_name=?,customer_phone=?,car_make=?,car_model=?,car_plate_no=? WHERE customer_id=?";
        jdbcTemplate.update(sql, customer.getCustomerName(), customer.getCustomerPhone(),
                customer.getCarMake(), customer.getCarModel(), customer.getCarPlateNo(),customerID);
    }

    public void deleteCustomer(Long customerID){
        String sql = "DELETE FROM CUSTOMER WHERE customer_id=?";
        jdbcTemplate.update(sql,customerID);
    }
}
