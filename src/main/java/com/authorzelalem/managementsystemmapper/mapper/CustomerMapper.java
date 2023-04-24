package com.authorzelalem.managementsystemmapper.mapper;

import com.authorzelalem.managementsystemmapper.dto.CustomerDto;
import com.authorzelalem.managementsystemmapper.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    CustomerDto modelToDto(Customer customer);
    Customer dtoToModel(CustomerDto customerDto);

}
