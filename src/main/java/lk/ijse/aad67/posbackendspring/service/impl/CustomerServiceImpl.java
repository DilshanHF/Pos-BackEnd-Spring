package lk.ijse.aad67.posbackendspring.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lk.ijse.aad67.posbackendspring.dao.CustomerDAO;
import lk.ijse.aad67.posbackendspring.dto.impl.CustomerDTO;
import lk.ijse.aad67.posbackendspring.dto.status.CustomerStatus;
import lk.ijse.aad67.posbackendspring.entity.impl.CustomerEntity;
import lk.ijse.aad67.posbackendspring.exceptions.CustomerNotFoundException;
import lk.ijse.aad67.posbackendspring.exceptions.DataPersistException;
import lk.ijse.aad67.posbackendspring.service.CustomerService;
import lk.ijse.aad67.posbackendspring.utill.Mapping;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private Mapping mapping;
    @PersistenceContext
    private EntityManager em;
    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        customerDTO.setId(generateCustomerId());
        CustomerEntity savedCustomer = customerDAO.save(mapping.toCustomerEntity(customerDTO));
        if (savedCustomer == null){
            throw new DataPersistException("Customer not saved");
        }


    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
       List<CustomerEntity> allCustomers = customerDAO.findAll();
       return mapping.toCustomerDTOList(allCustomers);
    }

    @Override
    public CustomerStatus getCustomer(String id) {
        return null;
    }

    @Override
    public void deleteCustomer(String id) {
        if(customerDAO.existsById(id)){
            customerDAO.deleteById(id);
        }else {
            throw new CustomerNotFoundException("Customer not found");
        }
    }

    @Override
    public void updateCustomer(String id, CustomerDTO customerDTO) {
       CustomerEntity customer = customerDAO.getReferenceById(id);
       if(customer == null){
           throw new CustomerNotFoundException("Customer not found");
       }
       customer.setName(customerDTO.getName());
       customer.setAddress(customerDTO.getAddress());
       customer.setContact(customerDTO.getContact());
       customerDAO.save(customer);
    }

    @Override
    public String generateCustomerId() {
        String jpql = "SELECT c.id FROM CustomerEntity c ORDER BY c.id DESC";
        TypedQuery<String> query = em.createQuery(jpql, String.class);

        query.setMaxResults(1);

        String maxCustomerId = null;
        try {
            maxCustomerId = query.getSingleResult();
        } catch (jakarta.persistence.NoResultException e) {
            return "C00-001";
        }

        int newCustomerId = Integer.parseInt(maxCustomerId.replace("C00-", "")) + 1;
        return String.format("C00-%03d", newCustomerId);
    }

    @Override
    public List<CustomerDTO> searchByContact(String value) {
        return null;
    }
}
