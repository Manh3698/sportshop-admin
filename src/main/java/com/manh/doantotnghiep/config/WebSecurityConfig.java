package com.manh.doantotnghiep.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.manh.doantotnghiep.config.fillter.JWTAuthenticationFilter;
import com.manh.doantotnghiep.config.fillter.JWTLoginFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private DataSource dataSource;
    
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests().antMatchers("/").permitAll();
//    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/").permitAll() // Có nghĩa là request "/" ko cần phải đc xác thực
                .antMatchers("/login").permitAll() // Request dạng POST tới "/login" luôn được phép truy cập dù là đã authenticated hay chưa
                .anyRequest().authenticated() // Các request còn lại đều cần được authenticated
                .and();
                // Add các filter vào ứng dụng của chúng ta, thứ mà sẽ hứng các request để xử lý trước khi tới các xử lý trong controllers.
                // Về thứ tự của các filter, các bạn tham khảo thêm tại http://docs.spring.io/spring-security/site/docs/3.0.x/reference/security-filter-chain.html mục 7.3 Filter Ordering
                //.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
               // .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN");
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("select username,password from users where  username=?")
//                .authoritiesByUsernameQuery("select username, name from users inner join roles on users.id_role = roles.id where username=?")
//                .passwordEncoder(new BCryptPasswordEncoder());
    }
    
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//                @Override
//                public void addCorsMappings(CorsRegistry registry) {
//                        registry.addMapping("/**").allowedMethods("*")
//                        .allowedOrigins("*")
//                        .allowedHeaders("*")
//                        .allowCredentials(true);
//                }
//        };
//}
}
