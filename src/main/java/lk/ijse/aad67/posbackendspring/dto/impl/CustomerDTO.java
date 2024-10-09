package lk.ijse.aad67.posbackendspring.dto.impl;

import lk.ijse.aad67.posbackendspring.dto.status.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO implements CustomerStatus {
    private String id;
    private String name;
    private String address;
    private String contact;
}
