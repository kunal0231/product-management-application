package com.product.exception;

public class CategoryAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CategoryAlreadyExistsException(String categoryName) {
		super("Category already exists with name: " + categoryName);
	}
}
