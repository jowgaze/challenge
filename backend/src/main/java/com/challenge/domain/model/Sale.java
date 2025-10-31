package com.challenge.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sales")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "sub_brand_id")
    private SubBrand subBrand;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "channel_id", nullable = false)
    private Channel channel;

    private String codSale1;
    private String codSale2;
    private LocalDateTime createdAt;
    private String customerName;
    private String saleStatusDesc;

    private Double totalAmountItems;
    private Double totalDiscount = 0.0;
    private Double totalIncrease = 0.0;
    private Double deliveryFee = 0.0;
    private Double serviceTaxFee = 0.0;
    private Double totalAmount;
    private Double valuePaid = 0.0;

    private Integer productionSeconds;
    private Integer deliverySeconds;
    private Integer peopleQuantity;

    private String discountReason;
    private String increaseReason;
    private String origin = "POS";

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProductSale> productSales;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DeliverySale> deliverySales;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Payment> payments;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CouponSale> couponSales;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DeliveryAddress> deliveryAddresses;
}