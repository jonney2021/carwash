package com.example.restservice.api;

import com.example.restservice.exception.ProductNotFoundException;
import com.example.restservice.model.Product;
import com.example.restservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Yeming Hu
 * @Date: 3/7/2023
 * @Description: com.example.restservice.api
 * @Version: 1.0
 */
@RestController
@RequestMapping("/carwash")
public class ProductController {
    @Autowired
    private ProductService productService;

    //get all products
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    // get product by id
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductByID(@PathVariable(name = "id") Long productID){
        try{
            return new ResponseEntity<>(productService.getProductByID(productID),HttpStatus.OK);
        }catch(ProductNotFoundException ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/products/product")
    public ResponseEntity<Product> getProductByType(@RequestParam(value = "name", defaultValue = "") String productType){
        try{
            return new ResponseEntity<>(productService.getProductByType(productType),HttpStatus.OK);
        }catch(ProductNotFoundException ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    // add product
    @PostMapping("/products")
    public ResponseEntity<Long> addProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.addProduct(product),HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Void> modifyProduct(@PathVariable(name = "id") Long productID, @RequestBody Product product){
        try{
            productService.modifyProduct(productID,product);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(ProductNotFoundException ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(name = "id") Long productID){
        try{
            productService.deleteProduct(productID);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(ProductNotFoundException ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
