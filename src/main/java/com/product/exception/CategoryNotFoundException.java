package com.product.exception;

public class CategoryNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CategoryNotFoundException(Long categoryId) {
		super("Category with id " + categoryId + " not found. Please create category first.");
	}
}
