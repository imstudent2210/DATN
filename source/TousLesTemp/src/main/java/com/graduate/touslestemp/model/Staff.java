package com.graduate.touslestemp.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

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
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "StaffGroup", referencedColumnName = "staffgroup_id")
    private StaffGroup staffGroup;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "Store", referencedColumnName = "id")
    private Store store;


}
