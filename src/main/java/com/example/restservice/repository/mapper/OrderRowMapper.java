package com.example.restservice.repository.mapper;

import com.example.restservice.repository.entity.OrderEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: Yeming Hu
 * @Date: 3/8/2023
 * @Description: com.example.restservice.repository.mapper
 * @Version: 1.0
 */
public class OrderRowMapper implements RowMapper<OrderEntity> {
    @Override
    public OrderEntity mapRow(ResultSet rs, int rowNum) throws SQLException {

        return OrderEntity.builder()
                .orderID(rs.getLong("order_id"))
                .amount(rs.getFloat("amount"))
//                .orderDate(rs.getTimestamp("order_date").toLocalDateTime())
                .orderDate(rs.getTimestamp("order_date").toLocalDateTime())
                .customerID(rs.getLong("customer_id"))
                .productID(rs.getLong("product_id"))
                .build();
    }
}
