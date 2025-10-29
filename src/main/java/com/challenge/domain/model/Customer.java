package com.challenge.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String email;
    private String phoneNumber;
    private String cpf;
    private LocalDate birthDate;
    private String gender;

    @ManyToOne
    @JoinColumn(name = "store_id")
    @JsonBackReference
    private Store store;

    @ManyToOne
    @JoinColumn(name = "sub_brand_id")
    @JsonBackReference
    private SubBrand subBrand;

    private String registrationOrigin;
    private Boolean agreeTerms = false;
    private Boolean receivePromotionsEmail = false;
    private Boolean receivePromotionsSms = false;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Sale> sales;
}