package com.authorzelalem.managementsystemmapper.mapper;

import com.authorzelalem.managementsystemmapper.dto.OrderDto;
import com.authorzelalem.managementsystemmapper.model.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderItemMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    OrderDto modelToDto(Orders orders);
    Orders dtoToModel(OrderDto orderDto);
}
