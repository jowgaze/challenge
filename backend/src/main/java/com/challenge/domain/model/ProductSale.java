package com.challenge.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "product_sales")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    @JsonBackReference
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference
    private Product product;

    private Double quantity;
    private Double basePrice;
    private Double totalPrice;
    private String observations;

    @OneToMany(mappedBy = "productSale", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ItemProductSale> itemProductSales;
}