package com.manh.doantotnghiep.bean.entity;

import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Table(name = "ProductCategories")
public class ProductCategoryEntity extends CommonEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }
}
