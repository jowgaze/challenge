package com.challenge.repositories;

import com.challenge.domain.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("""
        SELECT SUM(s.totalAmountItems) FROM Sale s
        WHERE s.channel.type = :channelType
        AND s.createdAt BETWEEN :start AND :end
    """)
    BigDecimal findTotalSales(@Param("channelType") String channelType, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);


    @Query("""
           SELECT ch.id AS channelId, ch.name AS channelName,
               st.id AS storeId, st.name AS storeName,
               COUNT(s.id) AS totalSales,
               SUM(s.totalAmount) AS revenue,
               SUM(s.totalAmount) / COUNT(s.id) AS avgTicket
           FROM Sale s
           JOIN s.channel ch
           JOIN s.store st
           WHERE s.saleStatusDesc = 'COMPLETED'
            AND st.id = :storeId
            AND ch.id = :channelId
             AND s.createdAt BETWEEN :start AND :end
           GROUP BY ch.id, ch.name, st.id, st.name
           ORDER BY SUM(s.totalAmount) / COUNT(s.id) DESC
           """)
    List<Object[]> findTicketByChannelAndStore(
            @Param("storeId") Long storeId,
            @Param("channelId") Long channelId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );
}