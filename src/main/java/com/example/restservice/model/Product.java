package com.example.restservice.model;

import lombok.*;

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
public class Product {
    private Long productID;
    private Float productPrice;
    private String productType;
//    private List<Order> orders;
}
