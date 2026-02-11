package com.product.service;

import java.util.List;

import com.product.dto.ProductRequestDTO;
import com.product.dto.ProductResponseDTO;

public interface ProductService {

	ProductResponseDTO addProduct(ProductRequestDTO dto);

	ProductResponseDTO getProductById(Long id);

	List<ProductResponseDTO> getAllProducts();

	ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto);

	void deleteProduct(Long id);

	List<ProductResponseDTO> getActiveProducts();

	List<ProductResponseDTO> addProducts(List<ProductRequestDTO> dtoList);

}
