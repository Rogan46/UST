package com.example.demo.config;

import com.example.demo.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Config {
    @Autowired
    private MyService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf(customizer->customizer.disable())
                .authorizeHttpRequests(request->request.anyRequest().authenticated())
        //http.formLogin(Customizer.withDefaults());

                .httpBasic(Customizer.withDefaults())
        .sessionManagement(session->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))


//        Customizer<CsrfConfigurer<HttpSecurity>> custcsrf=new Customizer<CsrfConfigurer<HttpSecurity>>() {
//            @Override
//            public void customize(CsrfConfigurer<HttpSecurity> httpSecurityCsrfConfigurer) {
//                httpSecurityCsrfConfigurer.disable();
//            }
//        };
//        http.csrf(custcsrf);
        .build();
   }
//@Bean
//    public UserDetailsService userDetailsService(){
//    UserDetails user1= User
//            .withDefaultPasswordEncoder()
//            .username("udhay")
//            .password("kumar")
//            .roles("ADMIN")
//            .build();
//    UserDetails user2= User
//            .withDefaultPasswordEncoder()
//            .username("akil")
//            .password("nandha")
//            .roles("USER")
//            .build();
//    return new InMemoryUserDetailsManager(user1,user2);
//}

        @Bean
        public AuthenticationProvider authenticationProvider(){
            DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
            provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
            provider.setUserDetailsService(userDetailsService);
            return provider;
        }
}
