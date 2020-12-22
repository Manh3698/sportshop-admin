package com.manh.doantotnghiep.bean.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailEntity {
    private String username;

    private String email;

    private String subject;

    private String message;

}
