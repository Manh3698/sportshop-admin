package com.manh.doantotnghiep.bean.entity;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "order_designs")
public class OrderDesignEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "quantity")
    private String email;

    @Column(name = "font")
    private String font;

    @Column(name = "image_list_number")
    private String imageListNumber;

    @Column(name = "total_price")
    private BigDecimal totalPrice = new BigDecimal(0);

    public OrderDesignEntity() {
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public Integer getProductId() {
        return this.productId;
    }

    public String getEmail() {
        return this.email;
    }

    public String getFont() {
        return this.font;
    }

    public String getImageListNumber() {
        return this.imageListNumber;
    }

    public BigDecimal getTotalPrice() {
        return this.totalPrice;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public void setImageListNumber(String imageListNumber) {
        this.imageListNumber = imageListNumber;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
