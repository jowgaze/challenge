package com.challenge.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "coupons")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    @JsonBackReference
    private Brand brand;

    @Column(nullable = false)
    private String code;

    @Column(length = 1)
    private String discountType; // 'p'=percentage, 'f'=fixed

    private Double discountValue;
    private Boolean isActive = true;
    private LocalDateTime validFrom;
    private LocalDateTime validUntil;

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CouponSale> couponSales;
}