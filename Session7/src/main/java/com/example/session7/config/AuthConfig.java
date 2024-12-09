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
                // Tắt tính năng CSRF để đơn giản hóa, nếu không cần chống giả mạo request
                .csrf(csrf -> csrf.disable())

                // Tắt kiểm soát CORS nếu không cần truy cập từ domain khác
                .cors(cors -> cors.disable())

                .authorizeHttpRequests(auth -> auth
                        // Chỉ cho phép người dùng có quyền hạn "ADMIN" truy cập vào đường dẫn "/home/admins"
                        .requestMatchers("/home/admins").hasRole("ADMIN")

                        //Chỉ cho phép người dùng có quyền hạn "ADMIN" hoặc "USER" truy cập vào đường dẫn "/home/users"
                        .requestMatchers("/home/users").hasAnyRole("ADMIN", "USER")

                        // Yêu cầu người dùng phải đăng nhập để truy cập vào "/home/authenticated"
                        .requestMatchers("/home/authenticated").authenticated()

                        // Cho phép tất cả người dùng truy cập vào các đường dẫn khác mà không cần đăng nhập
                        .anyRequest().permitAll()
                )

                // Khi yêu cầu truy cập bị từ chối, chuyển hướng đến trang"/auth/access/denied"
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/auth/access/denied")
                )

                .formLogin(formLogin -> formLogin
                        // Chỉ định đường dẫn đến trang đăng nhập
                        .loginPage("/auth/login/form")

                        //Đường dẫn để xử lý hành động đăng nhập
                        .loginProcessingUrl("/auth/login")

                        // Sau khi đăng nhập thành công, chuyển hướng đến "/auth/login/success" (false: Luôn không chuyển hướng)
                        .defaultSuccessUrl("/auth/login/success", false)

                        // Sau khi đăng nhập thất bại, chuyển hướng đến "/auth/login/error"
                        .failureUrl("/auth/login/error")

                        // Chỉ định tên parameter cho trường username trong form đăng nhập
                        .usernameParameter("username")

                        // Chỉ định tên parameter cho trường password trong form đăng nhập
                        .passwordParameter("password")
                )

                .logout(logout -> logout
                        // Đường dẫn để thực hiện hành động đăng xuất
                        .logoutUrl("/auth/logoff")

                        // Sau khi đăng xuất thành công, chuyển hướng đến "/auth/logoff/success"
                        .logoutSuccessUrl("/auth/logoff/success")
                )

                // OAuth2 - Đăng nhập từ mạng xã hội
                .oauth2Login(oauth2 -> oauth2
                        // Cấu hình trang đăng nhập tùy chỉnh cho OAuth2
                        .loginPage("/auth/login/form")

                        // Cấu hình URL sẽ chuyển hướng sau khi đăng nhập OAuth2 thành công
                        .defaultSuccessUrl("/oauth2/login/success", true)

                        // Cấu hình URL sẽ chuyển hướng nếu đăng nhập OAuth2 thất bại
                        .failureUrl("/auth/login/error")

                        // Cấu hình điểm cuối authorization của OAuth2
                        .authorizationEndpoint(endpoint -> endpoint
                                .baseUri("/oauth2/authorization")
                        )
                );

        // Kết thúc cấu hình và trả về một đối tượng SecurityFilterChain
        return httpSecurity.build();
    }
}
