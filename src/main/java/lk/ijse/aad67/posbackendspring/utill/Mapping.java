package lk.ijse.aad67.posbackendspring.utill;

import lk.ijse.aad67.posbackendspring.dto.impl.CustomerDTO;
import lk.ijse.aad67.posbackendspring.entity.impl.CustomerEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

//    customer mapping
    public CustomerEntity toCustomerEntity(CustomerDTO customerDTO){
        return modelMapper.map(customerDTO, CustomerEntity.class);

    }
    public CustomerDTO toCustomerDTO(CustomerEntity customerEntity){
        return modelMapper.map(customerEntity, CustomerDTO.class);
    }
    public List<CustomerDTO> toCustomerDTOList(List<CustomerEntity> customers) {
        return modelMapper.map(customers, new TypeToken<List<CustomerDTO>>() {}.getType());
    }
}
