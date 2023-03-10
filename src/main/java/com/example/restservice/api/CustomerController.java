package com.example.restservice.api;

import com.example.restservice.exception.CustomerExistException;
import com.example.restservice.exception.CustomerNotFoundException;
import com.example.restservice.model.Customer;
import com.example.restservice.service.CustomerService;
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
@CrossOrigin(maxAge = 45000)
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    //get all customers
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return new ResponseEntity<>(customerService.getAllCustomers(),HttpStatus.OK);
    }

    // get customer by id
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerByID(@PathVariable(name="id") Long customerID){
        try{
            return new ResponseEntity<>(customerService.getCustomerByID(customerID),HttpStatus.OK);
        }catch(CustomerNotFoundException ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    // get customer by name
    @GetMapping("/customers/customer")
    public ResponseEntity<Customer> getCustomerByName(@RequestParam(value = "name", defaultValue = "") String customerName){
        try{
            return new ResponseEntity<>(customerService.getCustomerByName(customerName),HttpStatus.OK);
        }catch(CustomerNotFoundException ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    // add customer
    @PostMapping("/customers")
    public ResponseEntity<Long> addCustomer(@RequestBody Customer customer){
        try{
            return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.CREATED);
        }catch(CustomerExistException ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.CONFLICT);
        }
    }

    //edit customer
    @PutMapping("/customers/{id}")
    public ResponseEntity<Void> editCustomer(@PathVariable(name = "id") Long customerID, @RequestBody Customer customer){
        try{
            customerService.editCustomer(customerID,customer);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(CustomerNotFoundException ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.NOT_FOUND);
        }catch(CustomerExistException ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.CONFLICT);
        }
    }

    //delete customer
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable(name = "id") Long customerID){
        try{
            customerService.deleteCustomer(customerID);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(CustomerNotFoundException ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
