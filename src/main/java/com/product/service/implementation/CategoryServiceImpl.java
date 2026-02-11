package com.product.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.dto.CategoryRequestDTO;
import com.product.dto.CategoryResponseDTO;
import com.product.entity.CategoryEntity;
import com.product.exception.CategoryAlreadyExistsException;
import com.product.exception.CategoryNotFoundException;
import com.product.exception.EmptyDtoException;
import com.product.mapper.CategoryMapper;
import com.product.repository.CategoryRepository;
import com.product.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;

	@Override
	public List<CategoryResponseDTO> createCategory(List<CategoryRequestDTO> dtos) {

		if (dtos == null || dtos.isEmpty()) {
			throw new EmptyDtoException();
		}

		List<CategoryEntity> entities = new ArrayList<>();

		for (CategoryRequestDTO dto : dtos) {

			if (categoryRepo.existsByCategoryName(dto.getCategoryName())) {
				throw new CategoryAlreadyExistsException(dto.getCategoryName());
			}

			CategoryEntity entity = new CategoryEntity();
			entity.setCategoryName(dto.getCategoryName());
			entity.setDescription(dto.getDescription());
			entity.setActive(true);

			entities.add(entity);
		}

		List<CategoryEntity> saved = categoryRepo.saveAll(entities);

		return saved.stream().map(CategoryMapper::toDto).toList();
	}

	@Override
	public List<CategoryResponseDTO> getAllCategories() {

		return categoryRepo.findAll().stream().map(CategoryMapper::toDto).toList();
	}

	@Override
	public void deleteCategory(Long id) {

		CategoryEntity category = categoryRepo.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));

		category.setActive(false);
		categoryRepo.save(category);
	}

	@Override
	public List<CategoryResponseDTO> getAllActiveCategories() {

		return categoryRepo.findByActiveTrue().stream().map(CategoryMapper::toDto).toList();
	}
}
