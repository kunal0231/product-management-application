package com.product.mapper;

import com.product.dto.CategoryResponseDTO;
import com.product.entity.CategoryEntity;

public class CategoryMapper {

	public static CategoryResponseDTO toDto(CategoryEntity entity) {
		CategoryResponseDTO dto = new CategoryResponseDTO(entity.getId(), entity.getCategoryName(),
				entity.getDescription());
		return dto;
	}
}
