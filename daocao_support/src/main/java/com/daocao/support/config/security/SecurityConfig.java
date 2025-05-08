package com.daocao.support.config.security;

import com.daocao.support.filter.JwtAuthenticateFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * @Project: daocao
 * @Description: SpringSecurity配置
 * @Author: bf
 * @Date: 2025/3/23 22:02:48
 * @Version: 1.0
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final SysUserDetailService sysUserDetailService;
    private final JwtAuthenticateFilter jwtAuthenticateFilter;

    /**
     * @Description: 配置过滤器链
     * @param http
     * @return SecurityFilterChain
     * @throws
     * @Author: bf
     * @Date: 2025/3/23 22:23
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 关闭csrf
        http.csrf(csrf -> csrf.disable());
        // 配置拦截策略
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/sys").permitAll()
                .anyRequest().authenticated());
        // 开启form认证
        http.formLogin(Customizer.withDefaults());
        // 配置跨域
        http.cors(cors -> {
            cors.configurationSource(configurationSource());
        });
        // 配置过滤器
        http.addFilterBefore(jwtAuthenticateFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /**
     * @Description: 配置跨域
     * @param
     * @return CorsConfigurationSource
     * @throws
     * @Author: bf
     * @Date: 2025/3/23 23:23
     */
    private CorsConfigurationSource configurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowedOrigins(List.of("*"));

        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**", configuration);
        return configurationSource;
    }

    // 创建 AuthenticationManager
    @Bean
    public AuthenticationManager sysUserAuthenticationManager() {
        // 创建 DaoAuthenticationProvider
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(sysUserDetailService);
        // 创建 ProviderManager 并设置 DaoAuthenticationProvider
        return new ProviderManager(provider);
    }


    /**
     * @Description: 配置密码编码器
     * @param
     * @return PasswordEncoder
     * @throws
     * @Author: bf
     * @Date: 2025/3/23 22:23
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
