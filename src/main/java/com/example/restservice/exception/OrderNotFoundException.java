package com.example.restservice.exception;

/**
 * @Author: Yeming Hu
 * @Date: 3/8/2023
 * @Description: com.example.restservice.exception
 * @Version: 1.0
 */
public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String message) {
        super(message);
    }
}
