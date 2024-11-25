package com.example.session6.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    HttpServletRequest request;

    @GetMapping("/home/index")
    public String index(Model model){
        model.addAttribute("message", "This is home page");
        return "home/index";
    }

    @GetMapping("/home/about")
    public String about(Model model){
        model.addAttribute("message", "This is introdution page");
        return "home/index";
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/home/admins")
    public String admins(Model model){
        if (!request.isUserInRole("ADMIN")){
            return "redirect:/auth/access/denied";
        }
        model.addAttribute("message", "Hello administrator");
        return "home/index";
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/home/users")
    public String users(Model model){
        if (request.isUserInRole("ADMIN") || request.isUserInRole("USER")){
            return "redirect:/auth/access/denied";
        }
        model.addAttribute("message", "Hello staff");
        return "home/index";
    }

//    @PreAuthorize("isAuthenticated()")
    @GetMapping("/home/authenticated")
    public String authenticated(Model model){
        if (request.getRemoteUser() == null){
            return "redirect:/auth/login/form";
        }
        model.addAttribute("message", "Hello authenticated user");
        return "home/index";
    }

}
