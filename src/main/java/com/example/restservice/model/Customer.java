package com.example.restservice.model;

import lombok.*;

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
public class Customer {
    private Long customerID;
    private String customerName;
    private String customerPhone;
    private String carMake;
    private String carModel;
    private String carPlateNo;
}
