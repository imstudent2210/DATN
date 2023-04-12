package com.graduate.touslestemp.domain.dto;

import com.graduate.touslestemp.domain.entity.Image;
import com.graduate.touslestemp.domain.entity.StaffGroup;
import com.graduate.touslestemp.domain.entity.StaffImage;
import com.graduate.touslestemp.domain.entity.Store;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class StaffDto {
    @Id
    private Long id;
    private String email;
    private String name;
    private String phone;
    private StaffGroup staffGroup;
    private Store store;
    Set<StaffImage> image;


}
