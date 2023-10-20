package com.example.pub.models.DTOs;

public class SummaryAllDTO {
    private String productName;
    private Integer amountSum;
    private Integer unitPrice;
    private Integer priceSum;

    public SummaryAllDTO(String productName, Integer amountSum, Integer unitPrice, Integer priceSum) {
        this.productName = productName;
        this.amountSum = amountSum;
        this.unitPrice = unitPrice;
        this.priceSum = priceSum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getAmountSum() {
        return amountSum;
    }

    public void setAmountSum(Integer amountSum) {
        this.amountSum = amountSum;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getPriceSum() {
        return priceSum;
    }

    public void setPriceSum(Integer priceSum) {
        this.priceSum = priceSum;
    }
}
