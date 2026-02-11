package com.product.mapper;

import com.product.dto.ProductRequestDTO;
import com.product.dto.ProductResponseDTO;
import com.product.entity.CategoryEntity;
import com.product.entity.ProductEntity;

public class ProductMapper {

    public static ProductEntity toEntity(ProductRequestDTO dto, CategoryEntity category) {
        ProductEntity p = new ProductEntity();
        p.setName(dto.getName());
        p.setPrice(dto.getPrice());
        p.setQuantity(dto.getQuantity());
        p.setCategory(category);
        p.setActive(true);
        return p;
    }

    public static ProductResponseDTO toDto(ProductEntity p) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setPrice(p.getPrice());
        dto.setQuantity(p.getQuantity());
        dto.setCategoryName(p.getCategory().getCategoryName());
        return dto;
    }
}
