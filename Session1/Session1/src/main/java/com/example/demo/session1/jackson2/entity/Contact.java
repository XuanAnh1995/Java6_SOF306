package com.example.demo.session1.jackson2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Contact {

    private String email;

    private String phone;

    private String address;

}
