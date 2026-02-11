package com.product.service.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.dto.ProductRequestDTO;
import com.product.dto.ProductResponseDTO;
import com.product.entity.CategoryEntity;
import com.product.entity.InventoryEntity;
import com.product.entity.ProductEntity;
import com.product.exception.CategoryNotFoundException;
import com.product.exception.EmptyDtoException;
import com.product.exception.ProductAlreadyInactiveException;
import com.product.exception.ProductNotFoundException;
import com.product.mapper.ProductMapper;
import com.product.repository.CategoryRepository;
import com.product.repository.InventoryRepository;
import com.product.repository.ProductRepository;
import com.product.service.ProductService;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private InventoryRepository inventoryRepo;

	@Override
	public ProductResponseDTO addProduct(ProductRequestDTO dto) {

		CategoryEntity category = categoryRepo.findById(dto.getCategoryId())
				.orElseThrow(() -> new CategoryNotFoundException(dto.getCategoryId()));

		ProductEntity product = ProductMapper.toEntity(dto, category);
		product.setCreatedAt(LocalDateTime.now());

		ProductEntity saved = productRepo.save(product);

		InventoryEntity inventory = new InventoryEntity();
		inventory.setProduct(saved);
		inventory.setAvailableStock(dto.getQuantity());
		inventory.setReservedStock(0);

		inventoryRepo.save(inventory);

		return ProductMapper.toDto(saved);
	}

	@Override
	public ProductResponseDTO getProductById(Long id) {
		ProductEntity product = productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
		return ProductMapper.toDto(product);

	}

	@Override
	public List<ProductResponseDTO> getAllProducts() {
		List<ProductEntity> allProd = productRepo.findAll();
		return allProd.stream().map(ProductMapper::toDto).toList();
	}

	@Override
	public ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto) {
		ProductEntity product = productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

		CategoryEntity category = categoryRepo.findById(dto.getCategoryId())
				.orElseThrow(() -> new CategoryNotFoundException(dto.getCategoryId()));

		product.setName(dto.getName());
		product.setPrice(dto.getPrice());
		product.setQuantity(dto.getQuantity());
		product.setCategory(category);
		product.setUpdatedAt(LocalDateTime.now());

		ProductEntity updated = productRepo.save(product);

		return ProductMapper.toDto(updated);
	}

	@Override
	public void deleteProduct(Long id) {

		ProductEntity product = productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
		if (!product.getActive()) {
			throw new ProductAlreadyInactiveException(id);
		}
		product.setActive(false); // 🔥 SOFT DELETE
		product.setUpdatedAt(LocalDateTime.now());

		productRepo.save(product);

	}

	@Override
	public List<ProductResponseDTO> getActiveProducts() {
		return productRepo.findByActiveTrue().stream().map(ProductMapper::toDto).toList();
	}

	@Override
	@Transactional
	public List<ProductResponseDTO> addProducts(List<ProductRequestDTO> dtoList) {

		if (dtoList == null || dtoList.isEmpty()) {
			throw new EmptyDtoException();
		}

		List<ProductEntity> products = new ArrayList<>();
		List<InventoryEntity> inventories = new ArrayList<>();

		for (ProductRequestDTO dto : dtoList) {

			CategoryEntity category = categoryRepo.findById(dto.getCategoryId())
					.orElseThrow(() -> new CategoryNotFoundException(dto.getCategoryId()));

			ProductEntity product = ProductMapper.toEntity(dto, category);
			product.setCreatedAt(LocalDateTime.now());

			products.add(product);
		}

		List<ProductEntity> savedProducts = productRepo.saveAll(products);

		// inventory create
		for (int i = 0; i < savedProducts.size(); i++) {

			ProductEntity savedProduct = savedProducts.get(i);
			ProductRequestDTO dto = dtoList.get(i);

			InventoryEntity inventory = new InventoryEntity();
			inventory.setProduct(savedProduct);
			inventory.setAvailableStock(dto.getQuantity());
			inventory.setReservedStock(0);

			inventories.add(inventory);
		}

		inventoryRepo.saveAll(inventories);

		return savedProducts.stream().map(ProductMapper::toDto).toList();
	}

}
