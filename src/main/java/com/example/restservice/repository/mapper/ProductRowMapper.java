package com.example.restservice.repository.mapper;

import com.example.restservice.repository.entity.ProductEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: Yeming Hu
 * @Date: 3/7/2023
 * @Description: com.example.restservice.repository.mapper
 * @Version: 1.0
 */
public class ProductRowMapper implements RowMapper<ProductEntity> {
    @Override
    public ProductEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ProductEntity.builder()
                .productID(rs.getLong("product_id"))
                .productPrice(rs.getFloat("product_price"))
                .productType(rs.getString("product_type"))
                .build();
    }
}
