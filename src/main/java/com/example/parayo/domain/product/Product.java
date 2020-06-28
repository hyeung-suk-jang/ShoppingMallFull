package com.example.parayo.domain.product;

import com.example.parayo.domain.baseEntity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    @JsonFormat
    @ApiModelProperty(value = "이름", required = false)
    @Column(name = "name", length = 40, nullable = true)
    private String name;

    @Lob
    @JsonFormat
    @Column(name = "description")
    private String description;

    @JsonFormat
    @Column(name = "price", nullable = true)
    private int price;

    @JsonFormat
    @Column(name = "category_id", nullable = true)
    private int categoryId;

    @Enumerated(EnumType.STRING)
    @JsonFormat
    private ProductStatus productStatus;

}
