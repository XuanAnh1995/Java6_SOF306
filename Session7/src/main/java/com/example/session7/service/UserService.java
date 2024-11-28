package com.example.session7.service;

import com.example.session7.entity.Account;
import com.example.session7.reposittory.AccountRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public UserService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        try {
            Account account = accountRepository.findById(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

            // Log kiểm tra thông tin account
            System.out.println("User found: " + account);

            String[] roles = account.getAuthorities().stream()
                    .map(authority -> authority.getRole().getId())
                    .toArray(String[]::new);

            return User.withUsername(username)
                    .password(account.getPassword())
                    .roles(roles)
                    .build();
        } catch (Exception e) {
            // Log chi tiết lỗi
            e.printStackTrace();
            throw new UsernameNotFoundException("Error while loading user: " + username, e);
        }
    }

}
