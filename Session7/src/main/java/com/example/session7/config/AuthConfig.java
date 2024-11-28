package com.example.session7.config;

import com.example.session7.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AuthConfig {

    @Autowired
    UserService userService;

    public AuthConfig(UserService userService) {
        this.userService = userService;
    }

    /* Mã hóa mật khẩu */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /* Cấu hình AuthenticationManager */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /* Phân quyền sử dụng và hình thức đăng nhập */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable()) // Tắt tính năng CSRF để đơn giản hóa, nếu không cần chống giả mạo request
                .cors(cors -> cors.disable()) // Tắt kiểm soát CORS nếu không cần truy cập từ domain khác
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/home/admins").hasRole("ADMIN")
                        .requestMatchers("/home/users").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/home/authenticated").authenticated()
                        .anyRequest().permitAll()
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/auth/access/denied")
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/auth/login/form")
                        .loginProcessingUrl("/auth/login")
                        .defaultSuccessUrl("/auth/login/success", false)
                        .failureUrl("/auth/login/error")
                        .usernameParameter("username")
                        .passwordParameter("password")
                )
                .logout(logout -> logout
                        .logoutUrl("/auth/logoff")
                        .logoutSuccessUrl("/auth/logoff/success")
                );

        return httpSecurity.build();
    }
}
