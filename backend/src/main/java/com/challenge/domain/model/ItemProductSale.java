package com.challenge.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "item_product_sales")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemProductSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_sale_id", nullable = false)
    @JsonBackReference
    private ProductSale productSale;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    @JsonBackReference
    private Item item;

    @ManyToOne
    @JoinColumn(name = "option_group_id")
    @JsonBackReference
    private OptionGroup optionGroup;

    private Double quantity;
    private Double additionalPrice;
    private Double price;
    private Double amount = 1.0;
    private String observations;

    @OneToMany(mappedBy = "itemProductSale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemItemProductSale> nestedItems;
}