package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.product.dto.ProductRequestDTO;
import com.product.dto.ProductResponseDTO;
import com.product.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping
	public ProductResponseDTO addProduct(@RequestBody ProductRequestDTO dto) {
		return productService.addProduct(dto);
	}

	@GetMapping("/{id}")
	public ProductResponseDTO getProductById(@PathVariable Long id) {
		return productService.getProductById(id);
	}

	@GetMapping("/active")
	public List<ProductResponseDTO> getActiveProducts() {
		return productService.getActiveProducts();
	}

	@GetMapping("/all")
	public List<ProductResponseDTO> getAllProducts() {
		return productService.getAllProducts();
	}

	@PutMapping("/{id}")
	public ProductResponseDTO updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO dto) {

		return productService.updateProduct(id, dto);
	}

	@DeleteMapping("/{id}")
	public String deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return "Product deleted successfully";
	}
	@PostMapping("/bulk")
	public List<ProductResponseDTO> addProducts(
	        @RequestBody List<ProductRequestDTO> dtoList) {
	    return productService.addProducts(dtoList);
	}

}
