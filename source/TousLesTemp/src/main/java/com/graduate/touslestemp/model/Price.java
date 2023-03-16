package com.graduate.touslestemp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Price")
public class Price {
    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="price_id")
    private SkuID id;
    private double price;
}
