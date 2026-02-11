package com.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

	List<ProductEntity> findByNameContainingIgnoreCase(String name);

	List<ProductEntity> findByActiveTrue();

	List<ProductEntity> findByPriceBetween(Double min, Double max);
}
