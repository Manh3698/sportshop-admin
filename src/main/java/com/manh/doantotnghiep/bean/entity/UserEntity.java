package com.manh.doantotnghiep.bean.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.manh.doantotnghiep.bean.entity.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
public class UserEntity extends CommonEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Integer id;

    @Column(name ="name")
    @JsonProperty("name")
    private String name;

    @Column(name ="role_id")
    @JsonProperty("role_id")
    private Integer roleId;

    @Column(name ="phone")
    @JsonProperty("phone")
    private Number phone;

    @Column(name ="email")
    @JsonProperty("email")
    private String email;

    @Column(name = "address")
    @JsonProperty("address")
    private String address;
}
