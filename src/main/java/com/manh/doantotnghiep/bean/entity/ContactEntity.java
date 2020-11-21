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
@Table(name ="contacts")
public class ContactEntity extends CommonEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Integer id;

    @Column(name = "username")
    @JsonProperty("username")
    private String username;

    @Column(name = "phone")
    @JsonProperty("phone")
    private Number phone;

    @Column(name = "email")
    @JsonProperty("email")
    private String email;

    @Column(name ="description")
    @JsonProperty("description")
    private String description;
}
