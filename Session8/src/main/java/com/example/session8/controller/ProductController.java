package com.example.session8.controller;

import com.example.session8.entity.Product;
import com.example.session8.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping("/product/list")
    public String list(Model model, @RequestParam("cid")Optional<String> cid) {
        if (cid.isPresent()){
            List<Product> list_Product = productRepository.fillCategoryById(cid.get());
            model.addAttribute("items", list_Product);
        } else {
            List<Product> list_Product = productRepository.findAll();
            model.addAttribute("items", list_Product);
        }
        return "product/list";
    }

    @RequestMapping("/product/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Product item = productRepository.findById(id).get();
        model.addAttribute("item", item);
        return "product/detail";
    }


}
