package com.product.exception;

public class ProductAlreadyInactiveException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProductAlreadyInactiveException(Long id) {
		super("Product with id " + id + " is already inactive");
	}
}