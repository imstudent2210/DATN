package com.graduate.touslestemp.domain.dto;

public class ProductDTO {
    private Long productId;
    private String description;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String image;
    private Integer inventory;
    private String name;
    private Long category;
    private Long productSize;
    private Long productIdSku;
    private Long sizeIdSku;

    public Long getProductId() {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public Integer getInventory() {
        return this.inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategory() {
        return this.category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public Long getProductSize() {
        return this.productSize;
    }

    public void setProductSize(Long productSize) {
        this.productSize = productSize;
    }

    public Long getProductIdSku() {
        return this.productIdSku;
    }

    public void setProductIdSku(Long productIdSku) {
        this.productIdSku = productIdSku;
    }

    public Long getSizeIdSku() {
        return this.sizeIdSku;
    }

    public void setSizeIdSku(Long sizeIdSku) {
        this.sizeIdSku = sizeIdSku;
    }
}
