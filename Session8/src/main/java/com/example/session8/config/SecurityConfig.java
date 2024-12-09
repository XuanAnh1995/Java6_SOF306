package com.example.session8.config;

import com.example.session8.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // Vô hiệu hóa CSRF cho ứng dụng không cần bảo vệ CSRF (ví dụ: API)
                .csrf(csrf -> csrf.disable())

                // Cấu hình phân quyền
                .authorizeHttpRequests(auth -> auth

                        // Yêu cầu người dùng phải đăng nhập để truy cập vào "/home/authenticated"
                        .requestMatchers("/order/**").authenticated()

                        //Chỉ cho phép người dùng có quyền hạn "ADMIN" hoặc "USER" truy cập vào đường dẫn "/admin/**"
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN", "USER")

                        // Chỉ cho phép người dùng có quyền hạn "ADMIN" truy cập vào đường dẫn "/rest/authorities"
                        .requestMatchers("/rest/authorities").hasRole("ADMIN")

                        // Cho phép tất cả người dùng truy cập vào các đường dẫn khác mà không cần đăng nhập
                        .anyRequest().permitAll()
                )

                // Cấu hình form login
                .formLogin(formLogin -> formLogin
                        .loginPage("/security/login/form")
                        .loginProcessingUrl("/security/login")
                        .defaultSuccessUrl("/security/login/success", false)
                        .failureUrl("/security/login/error")
                )

                // Cấu hình xử lý lỗi khi không có quyền
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/security/unauthoried")
                )

                // Cấu hình logout
                .logout(logout -> logout
                        .logoutUrl("/security/logoff")
                        .logoutSuccessUrl("/security/logoff/success")
                );

        return httpSecurity.build();
    }

    // Không cần sử dụng @Autowired cho BCryptPasswordEncoder
    // Chỉ cần khai báo bean và sử dụng trực tiếp khi cần
    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(HttpMethod.OPTIONS, "/**");
    }
}

