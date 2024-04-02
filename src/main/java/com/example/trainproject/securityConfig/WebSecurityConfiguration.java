package com.example.trainproject.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {
    private final SuccessHandler successHandler;
    private final UserDetailsService userDetailsService;

    public WebSecurityConfiguration(UserDetailsService userDetailsService, SuccessHandler successHandler) {
        this.successHandler = successHandler;
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    void registerProvider(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
        }

    @Bean
    public SecurityFilterChain getSpringSecurityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .formLogin(formLogin -> formLogin.loginPage("/train").successHandler(successHandler).loginProcessingUrl("/train")
                        .failureUrl("/login-error")
                        .usernameParameter("p_username")
                        .passwordParameter("p_password")
                        .permitAll());

        http.authorizeHttpRequests(auth -> auth.requestMatchers("/train").permitAll()
                .requestMatchers("/admin/**").hasRole("Admin")
                .requestMatchers("/user/**").hasRole("User")
                .anyRequest().authenticated());

        http.logout(logout -> logout.permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/train"));

        return http.build();


//        http.formLogin(formLogin -> formLogin.loginPage("/login").successHandler(successHandler).loginProcessingUrl("/login")
//                .failureUrl("/login-error")
//                .usernameParameter("p_username")
//                .passwordParameter("p_password")
//                .permitAll());
//
//        http.authorizeHttpRequests(authz ->authz.requestMatchers("/login").permitAll()
//                .requestMatchers("/admin/**").hasRole("ADMIN")
//                .requestMatchers("/user/**").hasRole("USER")
//                .anyRequest().authenticated());//.permitAll();
//
//        http.logout(logout -> logout.permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                        .logoutSuccessUrl("/login"));

//                .anyRequest().authenticated()//Для остальных действий необходимо пройти аутентификацию
//                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //Если необходимо не изменять id сессии поменять на ALWAYS
//        return http.build();
    }


}
