package com.challenge.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "option_groups")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionGroup {

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

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;

    @Column(nullable = false)
    private String name;

    private String posUuid;
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "optionGroup", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ItemProductSale> itemProductSales;
}