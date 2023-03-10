package com.example.restservice.repository;

import com.example.restservice.model.Order;
import com.example.restservice.model.Product;
import com.example.restservice.repository.entity.ProductEntity;
import com.example.restservice.repository.mapper.ProductOrderRowMapper;
import com.example.restservice.repository.mapper.ProductRowMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Yeming Hu
 * @Date: 3/7/2023
 * @Description: com.example.restservice.repository
 * @Version: 1.0
 */
@Repository
public class ProductRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ModelMapper modelMapper;

    public List<Product> getAllProduct(){
        List<ProductEntity> productEntities = jdbcTemplate.query("SELECT * FROM PRODUCT", new ProductRowMapper());
        List<Product> productResult = new ArrayList<>();
        for(ProductEntity productEntity: productEntities){
            Product product = modelMapper.map(productEntity,Product.class);
            productResult.add(product);
        }
        return productResult;
    }


    public Product getProductByID(Long productID){
        String sql="SELECT * FROM PRODUCT P LEFT JOIN `ORDER` O ON P.product_id=O.product_id WHERE P.product_id=?";
        try{
            ProductEntity productEntity = jdbcTemplate.queryForObject(sql,new ProductOrderRowMapper(),productID);
            Product product = modelMapper.map(productEntity, Product.class);
            return product;
        }catch(EmptyResultDataAccessException ex){
            return null;
        }
    }


    public Long addProduct(Product product){
        String sql="";
        sql="INSERT INTO PRODUCT(product_price, product_type) VALUES(?,?)";
        jdbcTemplate.update(sql,product.getProductPrice(),product.getProductType());
        Long productID = jdbcTemplate.queryForObject("SELECT MAX(product_id) FROM PRODUCT", Long.class);

        for(Order order:product.getOrders()){
            sql = "INSERT INTO `order`(amount,order_date,customer_id,product_id) VALUES(?,CURRENT_TIMESTAMP,?,?)";
            jdbcTemplate.update(sql,order.getAmount(),order.getCustomerID(),productID);
        }
        return productID;
    }

    public void modifyProduct(Long productID,Product product){
        String sql = "UPDATE PRODUCT SET product_price=?,product_type=? WHERE product_id=?";
        jdbcTemplate.update(sql,product.getProductPrice(),product.getProductType(),productID);
    }

    public void deleteProduct(Long productID){
        String sql = "DELETE FROM PRODUCT WHERE product_id=?";
        jdbcTemplate.update(sql,productID);
    }
}
