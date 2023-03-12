package com.graduate.touslestemp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.graduate.touslestemp.model.AccountRole;
import lombok.*;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
                                            
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long roleID;
    private String roleName;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "role")
    @JsonIgnore
    private Set<AccountRole> userRole = new HashSet<>();
}
