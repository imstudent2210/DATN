package com.graduate.touslestemp.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

/**
 * @File: Store.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:20 AM
 * @Update: 21/6/2023
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;
    @NotEmpty(message = "Enter store name !")
    private String name;
    private String phone;
    private String email;
    private String addressDetail;
    @ManyToOne
    @JoinColumn(name = "Address", referencedColumnName = "address_id")
    private Address address;
    private String image;
    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<Product> productList;
    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<Staff> staff;
}
