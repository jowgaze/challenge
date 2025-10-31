package com.challenge.repositories;

import com.challenge.domain.model.ProductSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductSaleRepository extends JpaRepository<ProductSale, Long> {
    @Query("""
            SELECT p.name AS name, SUM(ps.quantity) AS totalSold, SUM(ps.totalPrice) AS totalRevenue,
            RANK() OVER (ORDER BY SUM(ps.quantity) DESC) AS position
            FROM ProductSale ps JOIN Product p ON p.id = ps.product.id JOIN Sale s ON s.id = ps.sale.id
            WHERE s.saleStatusDesc = 'COMPLETED'
            AND (:storeId IS NULL OR s.store.id = :storeId)
            AND (:channelId IS NULL OR s.channel.id = :channelId)
            AND s.createdAt BETWEEN :start AND :end
            GROUP BY p.name ORDER BY totalSold DESC LIMIT 10
            """)
    List<Object[]> findTopProduct(@Param("storeId") Long storeId,
                                  @Param("channelId") Long channelId,
                                  @Param("start") LocalDateTime start,
                                  @Param("end") LocalDateTime end);
}