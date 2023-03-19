package com.graduate.touslestemp.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
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
    @NotEmpty(message = "Enter current quantity !")
    private int inventory;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category", referencedColumnName = "category_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "products")
    @JsonIgnore
    private Set<Store> tutorials = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ProductSize", referencedColumnName = "product_size_id")
    private ProductSize productSize;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(
                    name = "product_id_sku",
                    referencedColumnName = "product_id_sku"),
            @JoinColumn(
                    name = "size_id_sku",
                    referencedColumnName = "size_id_sku")
    })
    private Price price;

//    @Enumerated(EnumType.STRING)
//    private Gender gender;


}
