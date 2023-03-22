package com.graduate.touslestemp.domain.dto;

public class AddressDTO {
    private Long addressId;
    private String name;

    public Long getAddressId() {
        return this.addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
