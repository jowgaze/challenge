package com.challenge.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "delivery_sales")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliverySale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    @JsonBackReference
    private Sale sale;

    private String courierId;
    private String courierName;
    private String courierPhone;
    private String courierType;
    private String deliveredBy;
    private String deliveryType;
    private String status;
    private Double deliveryFee;
    private Double courierFee;
    private String timing;
    private String mode;
}