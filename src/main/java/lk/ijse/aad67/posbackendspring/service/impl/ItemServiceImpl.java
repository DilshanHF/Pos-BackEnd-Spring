package lk.ijse.aad67.posbackendspring.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lk.ijse.aad67.posbackendspring.dao.ItemDAO;
import lk.ijse.aad67.posbackendspring.dto.impl.ItemDTO;
import lk.ijse.aad67.posbackendspring.dto.status.ItemStatus;
import lk.ijse.aad67.posbackendspring.entity.impl.ItemEntity;
import lk.ijse.aad67.posbackendspring.exceptions.DataPersistException;
import lk.ijse.aad67.posbackendspring.service.ItemService;
import lk.ijse.aad67.posbackendspring.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemDAO itemDAO;
    @Autowired
    private Mapping mapping;
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void saveItem(ItemDTO itemDTO) {
        itemDTO.setId(generateItemId());
        ItemEntity savedItem = itemDAO.save(mapping.toItemEntity(itemDTO));
        if (savedItem == null) {
            throw new DataPersistException("Failed to add item");
        }
    }

    @Override
    public void updateItem(String id, ItemDTO itemDTO) {

    }

    @Override
    public void deleteItem(String id) {

    }

    @Override
    public ItemStatus getSelectedItem(String id) {
        return null;
    }

    @Override
    public List<ItemDTO> getAllItem() {
        return List.of();
    }

    @Override
    public String generateItemId() {

    }

    @Override
    public List<ItemDTO> searchByItemCode(String newItemCode) {
        return List.of();
    }
}
