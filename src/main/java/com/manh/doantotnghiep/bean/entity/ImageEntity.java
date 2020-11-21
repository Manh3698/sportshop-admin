package com.manh.doantotnghiep.bean.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.manh.doantotnghiep.bean.entity.CommonEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@Table(name = "images")
public class ImageEntity extends CommonEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Integer id;

    @Column(name = "image_id")
    @JsonProperty("image_id")
    private Integer imageId;

    @Column(name = "image_name")
    @JsonProperty("image_name")
    private String imageName;
}
