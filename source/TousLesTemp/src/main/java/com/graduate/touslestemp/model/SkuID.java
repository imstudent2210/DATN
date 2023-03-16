package com.graduate.touslestemp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class SkuID implements Serializable {
    @Column(name="product_id_sku")
    private Long productId;
    @Column(name="size_id_sku")
    private Long sizeId;

}
