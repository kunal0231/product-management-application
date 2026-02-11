package com.product.service;

import java.util.List;

import com.product.dto.CategoryRequestDTO;
import com.product.dto.CategoryResponseDTO;

public interface CategoryService {

	List<CategoryResponseDTO> createCategory(List<CategoryRequestDTO> dto);

	List<CategoryResponseDTO> getAllCategories();

	void deleteCategory(Long id);

	List<CategoryResponseDTO> getAllActiveCategories();
}
