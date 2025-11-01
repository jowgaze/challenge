package com.challenge.repositories;

import com.challenge.domain.model.Sale;
import com.challenge.dto.TicketDto;
import com.challenge.dto.balance.BalanceResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("""
        SELECT new com.challenge.dto.balance.BalanceResponseDto(s.channel.name, SUM(s.totalAmountItems)) FROM Sale s
        WHERE (:storeId IS NULL OR s.store.id = :storeId)
        AND s.createdAt BETWEEN :start AND :end
        GROUP BY s.channel.id, s.channel.name
        ORDER BY s.channel.id ASC
    """)
    List<BalanceResponseDto> findTotalSales(@Param("storeId") Long storeId, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);


    @Query("""
           SELECT new com.challenge.dto.TicketDto(
                      ch.id, ch.name, st.id, st.name,
               COUNT(s.id),
               SUM(s.totalAmount)
           )
           FROM Sale s
           JOIN s.channel ch
           JOIN s.store st
           WHERE s.saleStatusDesc = 'COMPLETED'
            AND (:storeId IS NULL OR st.id = :storeId)
            AND (:channelId IS NULL OR ch.id = :channelId)
             AND s.createdAt BETWEEN :start AND :end
           GROUP BY ch.id, ch.name, st.id, st.name
           ORDER BY SUM(s.totalAmount) / COUNT(s.id) DESC
           """)
    List<TicketDto> findTicketByChannelAndStore(
            @Param("storeId") Long storeId,
            @Param("channelId") Long channelId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );
}