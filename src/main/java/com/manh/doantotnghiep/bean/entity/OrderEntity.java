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
@Table(name = "Order")
public class OrderEntity extends CommonEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Integer id;

    @Column(name = "payment_method")
    @JsonProperty("payment_method")
    private String paymentMethod;

    @Column(name = "payment_status")
    @JsonProperty("payment_status")
    private String paymentStatus;

    @Column(name = "product_id")
    @JsonProperty("product_id")
    private Integer productId;

    @Column(name = "total_price")
    @JsonProperty("total_price")
    private BigDecimal totalPrice = new BigDecimal(0);

    @Column(name = "status")
    @JsonProperty("status")
    private String status;

    @Column(name = "user_id")
    @JsonProperty("user_id")
    private Integer userId;

    @Column(name = "user_address")
    @JsonProperty("user_address")
    private String userAddress;

    @Column(name = "user_phone")
    @JsonProperty("user_phone")
    private Number userPhone;

    @Column(name = "user_email")
    @JsonProperty("user_email")
    private String userEmail;

    @Column(name = "user_message")
    @JsonProperty("user_message")
    private String userMessage;
}
