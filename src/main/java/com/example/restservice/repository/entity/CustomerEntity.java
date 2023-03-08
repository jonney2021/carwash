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
public class CustomerEntity {
    private Long customerID;
    private String customerName;
    private String customerPhone;
    private String carMake;
    private String carModel;
    private String carPlateNo;
    private List<OrderEntity> orders;




}
