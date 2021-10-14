package com.example.desafiojavaspringboot.repositories;

import com.example.desafiojavaspringboot.domain.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products, Integer> {}
