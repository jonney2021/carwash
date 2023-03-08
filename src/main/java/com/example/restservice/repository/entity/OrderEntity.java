package com.example.restservice.repository.entity;

import com.example.restservice.model.Customer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: Yeming Hu
 * @Date: 3/7/2023
 * @Description: com.example.restservice.repository.entity
 * @Version: 1.0
 */

@Setter
@Getter
@Builder
public class OrderEntity {
    private Long orderID;
    private Float amount;
    private String orderDate;
    private Long customerID;
    private Long productID;
}
