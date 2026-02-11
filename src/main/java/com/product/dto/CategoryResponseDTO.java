package com.product.dto;

public class CategoryResponseDTO {

	private Long id;
	private String categoryName;
	private String categoryDescription;

	public CategoryResponseDTO(Long id, String categoryName, String categoryDescription) {
		this.id = id;
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
	}

	public Long getId() {
		return id;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryName() {
		return categoryName;
	}
}
