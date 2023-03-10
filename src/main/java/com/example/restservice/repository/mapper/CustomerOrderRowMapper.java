package com.example.restservice.repository.mapper;

import com.example.restservice.repository.entity.CustomerEntity;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @Author: Yeming Hu
 * @Date: 3/9/2023
 * @Description: com.example.restservice.repository.mapper
 * @Version: 1.0
 */
public class CustomerOrderRowMapper implements RowMapper<CustomerEntity> {


    @Override
    public CustomerEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        CustomerResultSetExtractor extractor = new CustomerResultSetExtractor();
        return extractor.extractData(rs);
    }
}
