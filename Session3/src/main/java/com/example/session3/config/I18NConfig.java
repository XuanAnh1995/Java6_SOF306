package com.example.session3.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
public class I18NConfig implements WebMvcConfigurer {

    // Cấu hình MessageSource để sử dụng các file chứa thông báo đa ngôn ngữ
    @Bean("messageSource")
    public MessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setDefaultEncoding("UTF-8"); // Đảm bảo encoding đúng
        ms.setBasenames("classpath:i18n/messages", "classpath:i18n/global"); // Nạp nhiều file nếu cần
        return ms;
    }

    // Cấu hình LocaleResolver sử dụng Cookie để lưu ngôn ngữ người dùng
    @Bean("localeResolver")
    public LocaleResolver getLocaleResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(new Locale("vi")); // Ngôn ngữ mặc định là tiếng Việt
        cookieLocaleResolver.setCookiePath("/"); // Đường dẫn áp dụng cookie
        cookieLocaleResolver.setCookieMaxAge(10 * 24 * 60 * 60); // Thời gian tồn tại cookie (10 ngày)
        return cookieLocaleResolver;
    }

    // Cấu hình LocaleChangeInterceptor để chuyển đổi ngôn ngữ thông qua tham số `lang`
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang"); // Tham số URL để thay đổi ngôn ngữ (vd: ?lang=en)
        registry.addInterceptor(localeChangeInterceptor)
                .addPathPatterns("/**") // Áp dụng cho tất cả các đường dẫn
                .excludePathPatterns("/images/**"); // Loại trừ các đường dẫn tĩnh
    }
}
