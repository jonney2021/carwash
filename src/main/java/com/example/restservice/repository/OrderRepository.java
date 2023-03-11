package com.example.restservice.repository;

import com.example.restservice.model.Order;
import com.example.restservice.repository.entity.OrderEntity;
import com.example.restservice.repository.mapper.OrderRowMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Yeming Hu
 * @Date: 3/8/2023
 * @Description: com.example.restservice.repository
 * @Version: 1.0
 */
@Repository
public class OrderRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ModelMapper modelMapper;

    public List<Order> getAllOrders() {
        List<OrderEntity> orderEntities = jdbcTemplate.query("SELECT * FROM `ORDER` O inner join product p on O.product_id = p.product_id;", new OrderRowMapper());
        List<Order> orderResult = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntities) {
            Order order = modelMapper.map(orderEntity, Order.class);
            orderResult.add(order);
        }
        return orderResult;
    }

    public Order getOrderByID(Long orderID) {
        String sql = "SELECT * FROM `ORDER` WHERE order_id=?";
        try {
            OrderEntity orderEntity = jdbcTemplate.queryForObject(sql, new OrderRowMapper(), orderID);
            Order order = modelMapper.map(orderEntity, Order.class);
            return order;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public Long addOrder(Order order) {
        String sql = "";
        sql = "INSERT INTO `ORDER`(amount,order_date,customer_id,product_id) VALUES(?,CURRENT_TIMESTAMP,?,?)";
        jdbcTemplate.update(sql, order.getAmount(), order.getCustomerID(), order.getProductID());
        Long orderID = jdbcTemplate.queryForObject("SELECT MAX(order_id) FROM `ORDER`", Long.class);
        return orderID;
    }

    public void editOrder(Long orderID, Order order) {
        String sql = "UPDATE `ORDER` SET amount=?,order_date=CURRENT_TIMESTAMP,customer_id=?,product_id=? WHERE order_id=?";
        jdbcTemplate.update(sql, order.getAmount(), order.getCustomerID(), order.getProductID(), orderID);
    }

    public void deleteOrder(Long orderID) {
        String sql = "DELETE FROM `ORDER` WHERE order_id=?";
        jdbcTemplate.update(sql, orderID);
    }
}
