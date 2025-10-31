package com.challenge.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "stores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    @JsonBackReference
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "sub_brand_id")
    @JsonBackReference
    private SubBrand subBrand;

    @Column(nullable = false)
    private String name;

    private String city;
    private String state;
    private String district;
    private String addressStreet;
    private Integer addressNumber;
    private String zipcode;
    private Double latitude;
    private Double longitude;

    private Boolean isActive = true;
    private Boolean isOwn = false;
    private Boolean isHolding = false;

    private LocalDate creationDate;
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Sale> sales;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Customer> customers;
}