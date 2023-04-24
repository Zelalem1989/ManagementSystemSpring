package com.authorzelalem.managementsystemmapper.mapper;

        import com.authorzelalem.managementsystemmapper.dto.MaterialDto;
        import com.authorzelalem.managementsystemmapper.model.Material;
        import org.mapstruct.Mapper;
        import org.mapstruct.factory.Mappers;

@Mapper
public interface MaterialMapper {

    MaterialMapper INSTANCE = Mappers.getMapper(MaterialMapper.class);
    MaterialDto modelToDto(Material material);
    Material dtoToModel(MaterialDto materialDto);

}
