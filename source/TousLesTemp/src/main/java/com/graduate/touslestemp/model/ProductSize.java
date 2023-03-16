package com.graduate.touslestemp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name="ProductSize")
public class ProductSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_size_id")
    private Long id;
    @NotEmpty(message = "Enter size !")
    private String size;
}
