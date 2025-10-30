package com.challenge.repositories;

import com.challenge.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("""
        SELECT c.id AS customer_id,c.customerName,
                   COUNT(s.id) AS total_purchases,
                   MAX(s.createdAt) AS last_purchase_date
        FROM Customer c
        JOIN Sale s ON s.customer.id = c.id
        GROUP BY c.id, c.customerName
        HAVING COUNT(s) >= 3
           AND MAX(s.createdAt) <= :thresholdDate
    """)
    List<Object[]> findInactiveCustomers(@Param("thresholdDate") LocalDateTime thresholdDate);
}