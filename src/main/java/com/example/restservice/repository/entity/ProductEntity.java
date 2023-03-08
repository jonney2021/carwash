package com.example.restservice.repository.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
public class ProductEntity {
    private Long productID;
    private Float productPrice;
    private String productType;
    private List<OrderEntity> orders;
}
