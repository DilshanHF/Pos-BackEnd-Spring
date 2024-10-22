package lk.ijse.aad67.posbackendspring.dao;

import lk.ijse.aad67.posbackendspring.entity.impl.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDAO extends JpaRepository<CustomerEntity,String> {
}
