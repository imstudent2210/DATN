package com.graduate.touslestemp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="AccountRole")
public class AccountRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userRoleId;


    @ManyToOne(fetch = FetchType.EAGER)

    private Admin admin;

    @ManyToOne(fetch = FetchType.EAGER)

    private Role role;


}
