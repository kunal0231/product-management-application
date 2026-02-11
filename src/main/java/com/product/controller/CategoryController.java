package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.product.dto.CategoryRequestDTO;
import com.product.dto.CategoryResponseDTO;
import com.product.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping
	public List<CategoryResponseDTO> createCategory(@RequestBody List<CategoryRequestDTO> dto) {

		return categoryService.createCategory(dto);
	}

	@GetMapping
	public List<CategoryResponseDTO> getAllCategories() {
		return categoryService.getAllCategories();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		return ResponseEntity.ok("Category deleted successfully");
	}

	@GetMapping("/active")
	public List<CategoryResponseDTO> getAllActiveCategories() {
		return categoryService.getAllActiveCategories();
	}
}
