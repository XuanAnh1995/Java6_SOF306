package com.example.session4.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Student {
    
    String email;
    
    String fullname;
    
    Double marks;
    
    Boolean gender;
    
    String country;
    
    @JsonIgnore
    public Object[] getArray(){
        return new Object[]{
            getEmail(),
            getFullname(),
            getMarks(),
            getGender(),
            getCountry()
        };
    }
    
}
