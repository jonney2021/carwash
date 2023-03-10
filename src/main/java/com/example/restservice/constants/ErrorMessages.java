package com.example.restservice.constants;

/**
 * @Author: Yeming Hu
 * @Date: 3/7/2023
 * @Description: com.example.restservice.constants
 * @Version: 1.0
 */
public class ErrorMessages {
    private ErrorMessages(){}

    public final static String CUSTOMER_EXIST_NAME = "Customer Exists with the name= %s";

    public final static String NO_CUSTOMER_FOUND_ID = "No Customer Found with id= %d";
    public final static String NO_PRODUCT_FOUND_ID = "No Product Found with id= %d";
    public final static String NO_PRODUCT_FOUND_TYPE = "No Product Found with type= %s";
    public final static String NO_ORDER_FOUND_ID = "No Order Found with id= %d";
}
