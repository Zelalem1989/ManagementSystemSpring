package com.authorzelalem.managementsystemmapper.mapper;

import com.authorzelalem.managementsystemmapper.dto.ProductDto;
import com.authorzelalem.managementsystemmapper.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto modelToDto(Product product);

    Product dtoToModel(ProductDto productDto);
}
