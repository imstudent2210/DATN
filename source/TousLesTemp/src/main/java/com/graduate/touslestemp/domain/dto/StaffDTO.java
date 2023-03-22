package com.graduate.touslestemp.domain.dto;

public class StaffDTO {
    private Long id;
    private String email;
    private String name;
    private String phone;
    private Long staffGroup;
    private Long store;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getStaffGroup() {
        return this.staffGroup;
    }

    public void setStaffGroup(Long staffGroup) {
        this.staffGroup = staffGroup;
    }

    public Long getStore() {
        return this.store;
    }

    public void setStore(Long store) {
        this.store = store;
    }
}
