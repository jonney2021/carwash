package com.example.restservice.model;

import com.example.restservice.repository.entity.ProductEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: Yeming Hu
 * @Date: 3/7/2023
 * @Description: com.example.restservice.model
 * @Version: 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Order {
    private Long orderID;
    private Float amount;
    private String orderDate;
    private Long customerID;
    private Long productID;
}
