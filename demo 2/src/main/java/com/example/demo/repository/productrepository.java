package com.example.demo.repository;


import com.example.demo.model.products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface productrepository extends JpaRepository<products, Long> {
    // all crud database methods
}