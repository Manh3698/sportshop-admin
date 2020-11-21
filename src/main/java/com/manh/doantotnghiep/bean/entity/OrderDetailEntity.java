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
@Table(name = "order_detail")
public class OrderDetailEntity extends CommonEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Integer id;

    @Column(name = "product_id")
    @JsonProperty("product_id")
    private Integer productId;

    @Column(name = "order_id")
    @JsonProperty("order_id")
    private Integer orderId;

    @Column(name = "quantity")
    @JsonProperty("quantity")
    private Integer quantity;

    @Column(name = "price_item")
    @JsonProperty("price_item")
    private BigDecimal priceItem = new BigDecimal(0);

    @Column(name = "total_price")
    @JsonProperty("tatal_price")
    private BigDecimal totalPrice = new BigDecimal(0);

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getPriceItem() {
        return priceItem;
    }

    public void setPriceItem(BigDecimal priceItem) {
        this.priceItem = priceItem;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
