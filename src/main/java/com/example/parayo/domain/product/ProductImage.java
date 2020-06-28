package com.example.parayo.domain.product;

import com.example.parayo.domain.baseEntity.BaseEntity;

import javax.persistence.Entity;

@Entity(name = "product_image")
public class ProductImage extends BaseEntity {

    private String path;
}
