package com.example.session7.controller;

import com.example.session7.entity.Authority;
import com.example.session7.reposittory.AccountRepository;
import com.example.session7.reposittory.AuthorityRepository;
import com.example.session7.reposittory.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthorityRestController {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;


    @GetMapping("/rest/authorities")
    public Map<String, Object> getAuthorities(){
        Map<String, Object> data = new HashMap<>();
        data.put("authorities", authorityRepository.findAll());
        data.put("accounts", accountRepository.findAll());
        data.put("roles", roleRepository.findAll());
        return data;
    }

    @DeleteMapping("/rest/authorities/{id}")
    public void delete(@PathVariable("id") Integer id){
        authorityRepository.deleteById(id);
    }

    @PostMapping("/rest/authorities")
    public Authority post(@RequestBody Authority authority){
        return authorityRepository.save(authority);
    }

}
