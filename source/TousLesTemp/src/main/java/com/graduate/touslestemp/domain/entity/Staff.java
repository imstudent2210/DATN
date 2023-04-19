package com.graduate.touslestemp.domain.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;


import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @NotEmpty(message = "Enter staff name !")
    private String name;
    @NotEmpty(message = "Enter staff email !")
    private String email;
    private String phone;
    @ManyToOne
    @JoinColumn(name = "StaffGroup", referencedColumnName = "staff_group_id")
    private StaffGroup staffGroup;

    @ManyToOne
    @JoinColumn(name = "Store", referencedColumnName = "store_id")
    private Store store;

    private String image;
}
