package com.challenge.repositories;

import com.challenge.domain.model.CouponSale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponSaleRepository extends JpaRepository<CouponSale, Long> {
}