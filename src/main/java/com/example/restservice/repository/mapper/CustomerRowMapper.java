package com.example.restservice.repository.mapper;

import com.example.restservice.repository.entity.CustomerEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: Yeming Hu
 * @Date: 3/7/2023
 * @Description: com.example.restservice.repository.mapper
 * @Version: 1.0
 */
public class CustomerRowMapper implements RowMapper<CustomerEntity> {

    @Override
    public CustomerEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return CustomerEntity.builder()
                .customerID(rs.getLong("customer_id"))
                .customerName(rs.getString("customer_name"))
                .customerPhone(rs.getString("customer_phone"))
                .carMake(rs.getString("car_make"))
                .carModel(rs.getString("car_model"))
                .carPlateNo(rs.getString("car_plate_no"))
                .build();
    }
}
