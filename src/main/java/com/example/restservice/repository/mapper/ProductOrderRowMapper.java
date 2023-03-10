package com.example.restservice.repository.mapper;

import com.example.restservice.repository.entity.ProductEntity;
import com.example.restservice.repository.mapper.ProductResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @Author: Yeming Hu
 * @Date: 3/9/2023
 * @Description: com.example.restservice.repository
 * @Version: 1.0
 */
public class ProductOrderRowMapper implements RowMapper<ProductEntity> {

    @Override
    public ProductEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductResultSetExtractor extractor = new ProductResultSetExtractor();
        return extractor.extractData(rs);
    }
}
