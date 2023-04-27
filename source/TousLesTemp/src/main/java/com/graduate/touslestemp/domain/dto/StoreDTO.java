package com.graduate.touslestemp.domain.dto;

import com.graduate.touslestemp.domain.entity.Address;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreDTO {
    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
    private String addressDetail;
    Address address;
    private String image;
}
