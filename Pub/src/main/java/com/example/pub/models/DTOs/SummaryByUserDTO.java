package com.example.pub.models.DTOs;

import com.example.pub.models.Commission;

public class SummaryByUserDTO {
    private Long userId;
    private String productName;
    private Integer price;

    public SummaryByUserDTO(Long id, Commission commission) {
        this.userId = getUserId();
        this.productName = commission.getProductName();
        this.price = commission.getPrice();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
