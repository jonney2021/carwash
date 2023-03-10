package com.example.restservice.repository.mapper;

import com.example.restservice.repository.entity.OrderEntity;
import com.example.restservice.repository.entity.ProductEntity;
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
public class ProductResultSetExtractor implements ResultSetExtractor<ProductEntity> {

    @Override
    public ProductEntity extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<OrderEntity> orderEntityList = new ArrayList<>();
        ProductEntity productEntity = null;
        if(rs.isFirst()){
            productEntity = ProductEntity.builder()
                    .productID(rs.getLong("product_id"))
                    .productPrice(rs.getFloat("product_price"))
                    .productType(rs.getString("product_type"))
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
        return productEntity;
    }
}
