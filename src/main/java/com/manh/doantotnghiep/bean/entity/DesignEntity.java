package com.manh.doantotnghiep.bean.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "designs")
public class DesignEntity extends CommonEntity implements Serializable {
    /**
     *
     */
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

    @Column(name ="font")
    private String description;

    @Column(name = "image_list_number")
    private String imageListNumber;


    public DesignEntity(Integer id, Integer userId, Integer productId, String email, String description, String imageListNumber) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.email = email;
        this.description = description;
        this.imageListNumber = imageListNumber;
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

    public String getDescription() {
        return this.description;
    }

    public String getImageListNumber() {
        return this.imageListNumber;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageListNumber(String imageListNumber) {
        this.imageListNumber = imageListNumber;
    }
}
