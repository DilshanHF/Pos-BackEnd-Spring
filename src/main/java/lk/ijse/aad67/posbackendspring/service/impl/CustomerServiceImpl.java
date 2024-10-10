package lk.ijse.aad67.posbackendspring.service.impl;

import lk.ijse.aad67.posbackendspring.dto.impl.CustomerDTO;
import lk.ijse.aad67.posbackendspring.dto.status.CustomerStatus;
import lk.ijse.aad67.posbackendspring.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Override
    public void saveCustomer(CustomerDTO CustomerDTO) {

    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        return List.of();
    }

    @Override
    public CustomerStatus getCustomer(String id) {
        return null;
    }

    @Override
    public void deleteCustomer(String id) {

    }

    @Override
    public void updateCustomer(String id, CustomerDTO customerDTO) {

    }
}
