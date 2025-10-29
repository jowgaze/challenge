package com.challenge.repositories;

import com.challenge.domain.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT SUM(s.totalAmountItems) FROM Sale s WHERE s.channel.type = :type AND s.createdAt BETWEEN :start AND :end")
    BigDecimal findTotalSales(@Param("type") String type, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

}