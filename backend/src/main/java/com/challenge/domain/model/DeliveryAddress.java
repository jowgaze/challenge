package com.challenge.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "delivery_addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    @JsonBackReference
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "delivery_sale_id")
    @JsonBackReference
    private DeliverySale deliverySale;

    private String street;
    private String number;
    private String complement;
    private String formattedAddress;
    private String neighborhood;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String reference;
    private Double latitude;
    private Double longitude;
}