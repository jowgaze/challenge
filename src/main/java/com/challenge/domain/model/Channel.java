package com.challenge.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "channels")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    @JsonBackReference
    private Brand brand;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(length = 1)
    private String type; // P=Presencial, D=Delivery

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Sale> sales;
}