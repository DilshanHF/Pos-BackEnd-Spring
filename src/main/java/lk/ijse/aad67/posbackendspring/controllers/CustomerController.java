package lk.ijse.aad67.posbackendspring.controllers;


import lk.ijse.aad67.posbackendspring.dto.impl.CustomerDTO;
import lk.ijse.aad67.posbackendspring.exceptions.CustomerNotFoundException;
import lk.ijse.aad67.posbackendspring.exceptions.DataPersistException;
import lk.ijse.aad67.posbackendspring.service.CustomerService;
import lk.ijse.aad67.posbackendspring.utill.Regex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping("/nextId")
    public String nextId(){
        return customerService.generateCustomerId();
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addCustomer(@RequestBody CustomerDTO customerDTO){
        try {
            boolean isContactValid = customerDTO.getContact().matches(Regex.CONTACT_REGEX);
            if(isContactValid){
                customerService.saveCustomer(customerDTO);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }else {
                logger.error("Contact is not valid");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }catch (DataPersistException e) {
            e.printStackTrace();
            logger.error("Faild with: ",e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (DataIntegrityViolationException e) {
            logger.error("Faild with: ",e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Faild with: ",e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDTO> getAllCustomers() {
       return customerService.getAllCustomer();
    }
    @DeleteMapping(value = "/{propertyId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("propertyId") String propertyId){
        boolean isCustomerIdValid = propertyId.matches(Regex.CUSTOMER_ID_REGEX);
        try {
            if(isCustomerIdValid){
                customerService.deleteCustomer(propertyId);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else {
                logger.error("Customer id is not valid");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }catch (CustomerNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PutMapping(value = "/{propertyId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCustomer(@PathVariable("propertyId") String propertyId,@RequestBody CustomerDTO customerDTO){
        boolean isCustomerIdValid = propertyId.matches(Regex.CUSTOMER_ID_REGEX);
        try{
            if(isCustomerIdValid){
                customerService.updateCustomer(propertyId,customerDTO);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else {
                logger.error("Customer id is not valid");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }catch (CustomerNotFoundException | DataPersistException e) {
            e.printStackTrace();
            logger.error("Faild with: ",e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }catch (Exception e){
            e.printStackTrace();
            logger.error("Faild with: ",e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
