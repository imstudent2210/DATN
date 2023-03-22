package com.graduate.touslestemp.config.authenticate;

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
    private Long accountRoleId;

    @ManyToOne(fetch = FetchType.EAGER)

    private Account account;

    @ManyToOne(fetch = FetchType.EAGER)

    private Role role;


}
