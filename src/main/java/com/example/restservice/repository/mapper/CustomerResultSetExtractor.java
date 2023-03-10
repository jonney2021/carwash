package com.example.restservice.repository.mapper;

import com.example.restservice.repository.entity.CustomerEntity;
import com.example.restservice.repository.entity.OrderEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Yeming Hu
 * @Date: 3/9/2023
 * @Description: com.example.restservice.repository.mapper
 * @Version: 1.0
 */
public class CustomerResultSetExtractor implements ResultSetExtractor<CustomerEntity> {
    @Override
    public CustomerEntity extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<OrderEntity> orderEntityList = new ArrayList<>();
        CustomerEntity customerEntity = null;
        if(rs.isFirst()){
            customerEntity = CustomerEntity.builder()
                    .customerID(rs.getLong("customer_id"))
                    .customerName(rs.getString("customer_name"))
                    .customerPhone(rs.getString("customer_phone"))
                    .carMake(rs.getString("car_make"))
                    .carModel(rs.getString("car_model"))
                    .carPlateNo(rs.getString("car_plate_no"))
                    .orders(orderEntityList)
                    .build();
            OrderEntity order = OrderEntity.builder()
                    .orderID(rs.getLong("order_id"))
                    .amount(rs.getFloat("amount"))
                    .orderDate(rs.getTimestamp("order_date").toLocalDateTime())
                    .customerID(rs.getLong("customer_id"))
                    .productID(rs.getLong("product_id"))
                    .build();

            orderEntityList.add(order);
        }

        while(rs.next()){
            OrderEntity order = OrderEntity.builder()
                    .orderID(rs.getLong("order_id"))
                    .amount(rs.getFloat("amount"))
                    .orderDate(rs.getTimestamp("order_date").toLocalDateTime())
                    .customerID(rs.getLong("customer_id"))
                    .productID(rs.getLong("product_id"))
                    .build();

            orderEntityList.add(order);
        }
        return customerEntity;
    }
}
