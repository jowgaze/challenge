package com.challenge.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sub_brands")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    @JsonBackReference
    private Brand brand;

    @Column(nullable = false)
    private String name;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "subBrand", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Store> stores;

    @OneToMany(mappedBy = "subBrand", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Category> categories;

    @OneToMany(mappedBy = "subBrand", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Product> products;

    @OneToMany(mappedBy = "subBrand", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Item> items;

    @OneToMany(mappedBy = "subBrand", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Customer> customers;
}