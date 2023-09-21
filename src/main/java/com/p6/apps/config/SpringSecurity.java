package com.p6.apps.config;

//import com.p6.apps.util.TbConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SpringSecurity /*extends WebSecurityConfigurerAdapter*/ {
    private static final String LOGIN_PROCESSING_URL = "/login?";
//    private static final String LOGIN_FAILURE_URL = "/login?error";
//    private static final String LOGIN_URL = "/login?";
//    private static final String LOGOUT_SUCCESS_URL = "/login?";

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder authBuilder) throws Exception {
        authBuilder.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT  email, password FROM  user where email=?")
                .authoritiesByUsernameQuery("SELECT email, roles FROM  user where email=?")
                .passwordEncoder(new BCryptPasswordEncoder()/*new Argon2PasswordEncoder(16,32,1,1<<14,2)*/)
        ;
    }



//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/balance/**").permitAll()
//                .antMatchers("/friend/**") .permitAll()
//                .antMatchers("/friends/**").permitAll()
//                .antMatchers("/signon/**") .permitAll()
//                .antMatchers(HttpMethod.GET).permitAll()
//                .antMatchers(HttpMethod.POST).permitAll()
//                .antMatchers(HttpMethod.PUT).permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().permitAll()
//                .loginProcessingUrl(LOGIN_PROCESSING_URL)
//                .failureUrl(LOGIN_FAILURE_URL)
//                .and().logout().logoutSuccessUrl(LOGOUT_SUCCESS_URL);
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests((authorize) ->
                authorize
                    .antMatchers("/user/**")                    .permitAll()
                    .antMatchers("/balance/**")                 .permitAll()
                    .antMatchers("/friends/**")                 .permitAll()
                    .antMatchers("/friend/**")                  .permitAll()
                    .antMatchers("/signon/**")                  .permitAll()
                    .antMatchers("/transactions")               .permitAll()
                    .antMatchers("/transactionByCreditor/**")   .permitAll()
                    .anyRequest().authenticated()
            ).formLogin(form ->
                form
                    .permitAll()
                    .loginProcessingUrl(LOGIN_PROCESSING_URL)
            ).logout(logout ->
                logout
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .permitAll()
            );
        return http.build();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/registration/**").permitAll()
//                        .requestMatchers("/login/**").permitAll()
//                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
//                        .requestMatchers("/admin/**").hasAnyRole("ADMIN")
//                        .anyRequest().authenticated()
//                )
//                .formLogin((form) -> form
//                        .loginPage("/login")
//                        .loginProcessingUrl("/login")
//                        .defaultSuccessUrl("/user/")
//                        .permitAll()
//                )
//                .logout(LogoutConfigurer::permitAll)
//                .exceptionHandling().accessDeniedPage("/access-denied");
//        return http.build();
//    }


}
