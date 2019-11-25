package com.example.demo.repositories;

import com.example.demo.entities.PendingOrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PendingOrderProductRepository extends JpaRepository<PendingOrderProduct, Long> {
}