package com.graduate.touslestemp.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "Salary")
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salary_id")
    private Long id;
    @NotEmpty(message = "Enter salary name !")
    private String name;
    @NotNull(message = "Enter salary !")
    private int basicSalary;
    @OneToMany(mappedBy = "salary", cascade = CascadeType.REMOVE)
    private List<TimeKeeping> timeKeepingList;
}
