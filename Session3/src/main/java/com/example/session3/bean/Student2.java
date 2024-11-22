package com.example.session3.bean;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


// NotNull : Đảm bảo giá trị không được là null.
// Có thể dùng cho bất kỳ loại dữ liệu nào (primitive types, wrappers, objects, collections, strings...).
// Chỉ kiểm tra giá trị không phải null
// Không kiểm tra xem chuỗi rỗng hay collection rỗng

// NotBlank : Đảm bảo giá trị không được là null, không rỗng và không chứa toàn khoảng trắng.
// Chỉ áp dụng cho String


// NotEmpty: Đảm bảo giá trị không được là null và không rỗng.
// String, Collection, Map, hoặc Array

public class Student2 {

    @NotBlank(message = "Không để trống email")
    @Email(message = "Không đúng định dạng email")
    private String email;

    @NotBlank(message = "Không để trống họ và tên")
    private String fullname;

    @NotNull(message = "Không để trống điểm")
    @PositiveOrZero(message = "Điểm phải lớn hơn hoặc bằng 0")
    @Max(value = 10, message = "Điểm phải nhỏ hơn hoặc bằng 10")
    private Double marks;

    @NotNull(message = "Chọn giới tính")
    private Boolean gender;

    @NotBlank(message = "Chọn quốc gia")
    private String country;

}