package com.challenge.repositories;

import com.challenge.domain.model.Customer;
import com.challenge.dto.customer.InactiveCustomerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("""
        SELECT new com.challenge.dto.customer.InactiveCustomerDto(c.id,c.customerName, COUNT(s.id), MAX(s.createdAt))
        FROM Customer c
        JOIN Sale s ON s.customer.id = c.id
        WHERE s.store.id = :storeId
        AND (:channelId IS NULL OR s.channel.id = :channelId)
        GROUP BY c.id, c.customerName
        HAVING COUNT(s) >= 3
           AND MAX(s.createdAt) <= :thresholdDate
    """)
    List<InactiveCustomerDto> findInactiveCustomers(@Param("storeId") Long storeId,
                                                    @Param("channelId") Long channelId,
                                                    @Param("thresholdDate") LocalDateTime thresholdDate);
}