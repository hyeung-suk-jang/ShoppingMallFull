package com.example.parayo.domain.product;

public enum ProductStatus {
    SELLABLE("판매중"),
    SOLD_OUT("품절");

    private String status;

    ProductStatus(String status) {
        this.status = status;
    }
}
