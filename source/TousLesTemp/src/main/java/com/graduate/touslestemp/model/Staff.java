package com.graduate.touslestemp.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @JoinColumn(name = "StaffGroup", referencedColumnName = "staff_group_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private StaffGroup staffGroup;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "Store", referencedColumnName = "store_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Store store;


}
