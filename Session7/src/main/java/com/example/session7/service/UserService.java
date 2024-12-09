package com.example.session7.service;

import com.example.session7.entity.Account;
import com.example.session7.reposittory.AccountRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final BCryptPasswordEncoder pe = new BCryptPasswordEncoder();

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

    public void loginFormOAuth2(OAuth2AuthenticationToken oauth2){
        String email = oauth2.getPrincipal().getAttribute("email");
        String password = Long.toHexString(System.currentTimeMillis());

        UserDetails user = User.withUsername(email)
                .password(pe.encode(password)).roles("GUEST").build();
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

}
