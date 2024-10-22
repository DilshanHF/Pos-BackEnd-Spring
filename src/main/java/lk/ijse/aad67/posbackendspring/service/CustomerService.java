package lk.ijse.aad67.posbackendspring.service;

import lk.ijse.aad67.posbackendspring.dto.impl.CustomerDTO;
import lk.ijse.aad67.posbackendspring.dto.status.CustomerStatus;

import java.util.List;

public interface CustomerService {
    void saveCustomer(CustomerDTO CustomerDTO);
    List<CustomerDTO> getAllCustomer();
    CustomerStatus getCustomer(String id);
    void deleteCustomer(String id);
    void updateCustomer(String id, CustomerDTO customerDTO);
    String generateCustomerId();
    List<CustomerDTO> searchByContact(String value);
}
