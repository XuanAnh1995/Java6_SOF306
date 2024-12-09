package com.example.session8.service;

import com.example.session8.entity.Account;
import com.example.session8.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        try {
            Account account = accountRepository.findById(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

            String[] roles = account.getAuthorities().stream()
                    .map(authority -> authority.getRole().getId())
                    .toArray(String[]::new);

            return User.withUsername(username)
                    .password(account.getPassword())
                    .roles(roles)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("Error while loading user: " + username, e);
        }
    }
}


//    public void loginFormOAuth2(OAuth2AuthenticationToken oauth2){
//        String email = oauth2.getPrincipal().getAttribute("email");
//        String password = Long.toHexString(System.currentTimeMillis());
//
//        UserDetails user = User.withUsername(email)
//                .password(pe.encode(password)).roles("GUEST").build();
//        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(auth);
//    }

