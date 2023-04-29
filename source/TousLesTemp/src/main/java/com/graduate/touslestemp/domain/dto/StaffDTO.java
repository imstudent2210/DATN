package com.graduate.touslestemp.domain.dto;

import com.graduate.touslestemp.domain.entity.StaffGroup;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffDTO {
    @Id
    private Long id;
    private String email;
    private String name;
    private String phone;
    private StaffGroup staffGroup;
    private StoreDTO store;
    private String image;
}
