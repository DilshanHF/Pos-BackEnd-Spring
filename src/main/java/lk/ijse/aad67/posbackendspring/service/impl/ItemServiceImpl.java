package lk.ijse.aad67.posbackendspring.service.impl;

import lk.ijse.aad67.posbackendspring.dto.impl.ItemDTO;
import lk.ijse.aad67.posbackendspring.dto.status.ItemStatus;
import lk.ijse.aad67.posbackendspring.service.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    @Override
    public void saveItem(ItemDTO itemDTO) {

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
        return "";
    }

    @Override
    public List<ItemDTO> searchByItemCode(String newItemCode) {
        return List.of();
    }
}
