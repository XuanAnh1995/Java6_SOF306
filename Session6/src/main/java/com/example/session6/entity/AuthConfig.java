package com.example.session6.entity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity // Kích hoạt bảo mật Spring Security
//@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class AuthConfig {

    /*
     * Bean dùng để mã hóa mật khẩu.
     * BCryptPasswordEncoder là thuật toán mã hóa phổ biến, đảm bảo tính bảo mật cao.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     * Quản lý dữ liệu người dùng trong bộ nhớ (InMemory).
     * Thay vì lấy thông tin từ cơ sở dữ liệu, chúng ta định nghĩa sẵn danh sách người dùng ở đây.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        // Tạo người dùng có quyền GUEST
        UserDetails user1 = User.withUsername("user1")
                .password(passwordEncoder().encode("123")) // Mật khẩu được mã hóa
                .roles("GUEST") // Chỉ có quyền GUEST
                .build();

        // Tạo người dùng có quyền USER và GUEST
        UserDetails user2 = User.withUsername("user2")
                .password(passwordEncoder().encode("123")) // Mật khẩu được mã hóa
                .roles("USER", "GUEST") // Có quyền USER và GUEST
                .build();

        // Tạo người dùng có quyền ADMIN, USER, và GUEST
        UserDetails user3 = User.withUsername("user3")
                .password(passwordEncoder().encode("123")) // Mật khẩu được mã hóa
                .roles("ADMIN", "USER", "GUEST") // Có tất cả các quyền
                .build();

        // Trả về danh sách người dùng được quản lý bởi InMemoryUserDetailsManager
        return new InMemoryUserDetailsManager(user1, user2, user3);
    }

    /*
     * Phân quyền và cấu hình hình thức đăng nhập
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Tắt CSRF (Cross-Site Request Forgery) nếu không cần sử dụng
                .csrf(csrf -> csrf.disable())

                // Tắt CORS (Cross-Origin Resource Sharing) nếu không cần
                .cors(cors -> cors.disable())

                // Phân quyền truy cập các đường dẫn
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/home/admins").hasRole("ADMIN") // Chỉ quyền ADMIN được truy cập
                        .requestMatchers("/home/users").hasAnyRole("ADMIN", "USER") // ADMIN và USER được truy cập
                        .requestMatchers("/home/authenticated").authenticated() // Bắt buộc phải đăng nhập
                        .anyRequest().permitAll() // Các đường dẫn khác không yêu cầu đăng nhập
                )
                // Xử lý lỗi khi truy cập đường dẫn không đúng vai trò
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/auth/access/denied") // Chuyển đến trang lỗi truy cập
                )

                // Cấu hình đăng nhập
                .formLogin(formLogin -> formLogin
                        .loginPage("/auth/login/form") // Trang đăng nhập tùy chỉnh
                        .loginProcessingUrl("/auth/login") // URL xử lý đăng nhập
                        .defaultSuccessUrl("/auth/login/success", false) // URL chuyển hướng khi đăng nhập thành công
                        .failureUrl("/auth/login/error") // URL chuyển hướng khi đăng nhập thất bại
                        .usernameParameter("username") // Tên tham số username trong form đăng nhập
                        .passwordParameter("password") // Tên tham số password trong form đăng nhập
                )
                // Cấu hình đăng xuất
                .logout(logout -> logout
                        .logoutUrl("/auth/logoff") // URL để thực hiện đăng xuất
                        .logoutSuccessUrl("/auth/logoff/success") // URL chuyển hướng khi đăng xuất thành công
                );

        // Xây dựng và trả về cấu hình bảo mật
        return http.build();
    }
}
