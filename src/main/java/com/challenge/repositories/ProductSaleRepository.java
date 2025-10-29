package com.challenge.repositories;

import com.challenge.domain.model.ProductSale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSaleRepository extends JpaRepository<ProductSale, Long> {
}