package lk.ijse.aad67.posbackendspring.service;

import lk.ijse.aad67.posbackendspring.dto.impl.ItemDTO;
import lk.ijse.aad67.posbackendspring.dto.status.ItemStatus;

import java.util.List;

public interface ItemService {
    void saveItem(ItemDTO itemDTO);
    void updateItem(String id, ItemDTO itemDTO);
    void deleteItem(String id);
    ItemStatus getSelectedItem(String id);
    List<ItemDTO> getAllItem();
    String generateItemId();
    List<ItemDTO> searchByItemCode(String newItemCode);
}
