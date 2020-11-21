package com.manh.doantotnghiep.bean.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.manh.doantotnghiep.bean.entity.CommonEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Setter
@Getter
@Table(name = "products")
public class ProductEntity extends CommonEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Integer id;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Column(name = "image")
    @JsonProperty("image")
    private String image;

    @Column(name ="new_price")
    @JsonProperty("new_price")
    private BigDecimal newPrice = new BigDecimal(0);

    @Column(name ="old_price")
    @JsonProperty("old_price")
    private BigDecimal oldPrice = new BigDecimal(0);

    @Column(name ="color")
    @JsonProperty("color")
    private String color;

    @Column(name ="category_id")
    @JsonProperty("category_id")
    private Integer categoryId;

    @Column(name ="status")
    @JsonProperty("status")
    private  String status;

    @Column(name ="size")
    @JsonProperty("size")
    private String size;

    @Column(name ="quantity")
    @JsonProperty("quantity")
    private Integer quantity;

    @Column(name ="description")
    @JsonProperty("description")
    private String description;
}
