package ru.itmo.soalab2.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//установить некоторые особенности конфигурации безопасности.
@Configurable
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {


//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

    //включаем cors-запросы, если тестим на локалке
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200").allowedMethods("*");
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Выключем стандартную аутентификацию спринга логин:пароль с каждым запросом
                .httpBasic().disable()
                // выключаем защиту от CSRF атак(защиты от перекрестных запросов)
                .csrf().disable()
                //не сохранять состояние
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                .and()
//                //для запросов get и post с url /api/points/* требуется аутентификация
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/api/points/**").authenticated()
//                .antMatchers(HttpMethod.POST, "/api/points/**").authenticated()
//                .antMatchers(HttpMethod.PUT, "/api/points/**").authenticated()
//                .antMatchers(HttpMethod.DELETE, "/api/points/**").authenticated()
//                .antMatchers(HttpMethod.POST, "/api/users/logout").authenticated()
//                //для остальных запросов аутентификация не требуется
//                .anyRequest().permitAll()
//                .and()
//                //применяем систему аутентификации
//                .apply(new SecurityConfigurer(tokenProvider));
    }


}
