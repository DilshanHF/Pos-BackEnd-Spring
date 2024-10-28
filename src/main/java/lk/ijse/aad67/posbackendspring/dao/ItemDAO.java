package lk.ijse.aad67.posbackendspring.dao;

import lk.ijse.aad67.posbackendspring.entity.impl.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDAO extends JpaRepository<ItemEntity, String> {

}
