package com.example.session3.controller;

import com.example.session3.bean.Student2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ValidationController {

    @ModelAttribute("countries")
    public Map<String, String> getCountries(){
        Map<String, String> countries = new HashMap<>();
        countries.put("VN", "Việt Nam");
        countries.put("US", "United States");
        countries.put("JP", "Japan");
        return countries;
    }

    @GetMapping("/validation/form")
    public String form(Model model){
        Student2 student2 = new Student2();
        model.addAttribute("student2", student2);
        return "validation/form";
    }

//    @PostMapping("/validation/validate")
//    public String save(Model model, @Validated @ModelAttribute("student2") Student2 form, Errors errors){
//        if (errors.hasErrors()){
//            model.addAttribute("message", "Vui lòng sửa các lỗi sau");
//            return "validation/form";
//        }
//        return "validation/success";
//    }

    @PostMapping("/validation/validate")
    public String save(Model model, @Validated @ModelAttribute("student2") Student2 form, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("message", "Please fix the following errors");
            return "validation/form";
        }
        return "validation/success";
    }


}
