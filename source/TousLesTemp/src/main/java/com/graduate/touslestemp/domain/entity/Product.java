package com.graduate.touslestemp.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    @NotEmpty(message = "Enter product name !")
    private String name;
    private String description;
    @NotNull(message = "Enter current quantity !")
    private int inventory;
    private double price;

    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "size", referencedColumnName = "size_id")
    private Size size;

    @ManyToOne
    @JoinColumn(name = "store", referencedColumnName = "store_id")
    private Store store;

    private String image;
}
