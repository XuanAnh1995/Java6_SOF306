package com.example.session8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShoppingCartController {

    @GetMapping("/cart/view")
    public String view(){
        return "cart/view";
    }

}
