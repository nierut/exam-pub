package com.example.pub.models.DTOs;

import com.example.pub.models.Commission;

public class SummaryByProductDTO {
    private Long userId;
    private Integer amount;
    private Integer price;

    public SummaryByProductDTO(Long id, Commission commission) {
        this.userId = id;
        this.amount = commission.getAmount();
        this.price = commission.getPrice();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
