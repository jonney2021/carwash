package com.example.restservice.service;

import com.example.restservice.model.Product;

import java.util.List;

/**
 * @Author: Yeming Hu
 * @Date: 3/7/2023
 * @Description: com.example.restservice.service
 * @Version: 1.0
 */
public interface ProductService {
    /**
     *
     * @return the list of products
     */
    List<Product> getAllProducts();

    /**
     *
     * @param productID the id of the product
     * @return
     */
    Product getProductByID(Long productID);

    /**
     *
     * @param product
     * @return the number of the product
     */
    Long addProduct(Product product);

    /**
     *
     * @param productID the id of the product
     * @param product a product with that id
     */
    void modifyProduct(Long productID,Product product);

    /**
     *
     * @param productID the id of that product
     */
    void deleteProduct(Long productID);


}
