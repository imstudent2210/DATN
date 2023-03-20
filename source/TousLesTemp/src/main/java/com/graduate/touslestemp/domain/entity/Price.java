//package com.graduate.touslestemp.domain.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name="Price")
//public class Price {
//    @EmbeddedId
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="price_id")
//    private SkuID id;
//    private double price;
//
//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "size", referencedColumnName = "size_id")
//    private Size size;
//}
