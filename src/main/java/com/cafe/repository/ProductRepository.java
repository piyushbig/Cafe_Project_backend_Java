package com.cafe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafe.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	public Optional<Product> findByName(String name);
	public Optional<Product> findById(int id);
	public List<Product> findByUserId(int userid);
	public List<Product> findAll();
}
