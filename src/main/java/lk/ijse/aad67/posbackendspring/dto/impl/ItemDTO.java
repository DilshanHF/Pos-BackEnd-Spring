package lk.ijse.aad67.posbackendspring.dto.impl;

import lk.ijse.aad67.posbackendspring.dto.status.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO implements ItemStatus {
    private String id;
    private String description;
    private double unitPrice;
    private int qty;
}
