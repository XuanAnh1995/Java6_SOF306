package com.example.demo.session1.lambda.entity;

import lombok.*;

// Tại sao dùng @Data mà không sử dụng @Getter và @Setter ?
// @Data bao gồm các tính năng của cả @Getter, @Setter, @ToString, @EqualsAndHashCode, và @RequiredArgsConstructor
// @Data thường được sử dụng khi bạn muốn một class có đầy đủ các phương thức cơ bản trên mà không cần phải khai báo riêng từng annotation
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Student {

    private String name;

    private Boolean gender = false;

    private Double marks = 0.0;

}
