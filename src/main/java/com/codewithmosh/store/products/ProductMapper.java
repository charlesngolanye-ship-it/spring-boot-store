package com.codewithmosh.store.products;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    // Outbound: Maps the nested object property to the flat DTO field
@Mapping(target = "categoryId", source = "category.id")
ProductDto toDto(Product product);

// Inbound: Maps the flat request field into the nested Entity object property
//@Mapping(target = "category.id", source = "categoryId")
//Product toEntity(CreateProductRequest request);

Product toEntity(ProductDto productDto);

//@Mapping(target = "id", ignore = true)
//void update(UpdateProductRequest request, @MappingTarget Product product);


@Mapping(target = "id", ignore = true)
void update(ProductDto productDto, @MappingTarget Product product);
}

/**
 * @Mapping used to customize mapping
 * We go into the source object which is the Product entity and map category.id to the categoryId field of the target object
 * which is ProductDto
 */