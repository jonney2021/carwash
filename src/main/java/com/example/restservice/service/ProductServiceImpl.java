package com.example.restservice.service;

import com.example.restservice.exception.ProductNotFoundException;
import com.example.restservice.model.Product;
import com.example.restservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.restservice.constants.ErrorMessages.NO_PRODUCT_FOUND_ID;

/**
 * @Author: Yeming Hu
 * @Date: 3/7/2023
 * @Description: com.example.restservice.service
 * @Version: 1.0
 */
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProduct();
    }

    @Override
    public Product getProductByID(Long productID) {
        Product productFound = productRepository.getProductByID(productID);
        if(productFound == null){
            throw new ProductNotFoundException(String.format(NO_PRODUCT_FOUND_ID,productID));
        }
        return productFound;
    }

    @Override
    public Long addProduct(Product product) {
        return productRepository.addProduct(product);
    }

    @Override
    public void modifyProduct(Long productID, Product product) {
        //validateID
        Product productFoundByID = productRepository.getProductByID(productID);
        if(productFoundByID == null){
            throw new ProductNotFoundException(String.format(NO_PRODUCT_FOUND_ID,productID));
        }
        productRepository.modifyProduct(productID,product);

    }

    @Override
    public void deleteProduct(Long productID) {
        Product productFoundByID = productRepository.getProductByID(productID);
        if(productFoundByID == null){
            throw new ProductNotFoundException(String.format(NO_PRODUCT_FOUND_ID,productID));
        }
        productRepository.deleteProduct(productID);
    }
}
