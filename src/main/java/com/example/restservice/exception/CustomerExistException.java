package com.example.restservice.exception;

/**
 * @Author: Yeming Hu
 * @Date: 3/7/2023
 * @Description: com.example.restservice.exception
 * @Version: 1.0
 */
public class CustomerExistException extends RuntimeException{
    public CustomerExistException(String message) {
        super(message);
    }
}
