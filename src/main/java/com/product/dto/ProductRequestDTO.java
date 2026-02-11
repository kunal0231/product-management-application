package com.product.dto;

public class ProductRequestDTO {
	private String name;
	private Double price;
	private Integer quantity;
	private Long categoryId;

	public ProductRequestDTO() {
		super();
	}

	public ProductRequestDTO(String name, Double price, Integer quantity, Long category) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.categoryId = categoryId;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

}
