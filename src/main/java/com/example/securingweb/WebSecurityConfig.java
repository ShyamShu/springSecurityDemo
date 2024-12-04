package com.example.securingweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
public class WebSecurityConfig {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtFilter jwtFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
//		Customizer<CsrfConfigurer<HttpSecurity>> customCsrf = new Customizer<CsrfConfigurer<HttpSecurity>>() {
//
//			@Override
//			public void customize(CsrfConfigurer<HttpSecurity> customizer) {
//				customizer.disable();
//			}	
//		};
//		
//		http.csrf(customCsrf);
//		return http.build();
	
		  http.csrf((csrf) -> csrf.disable());
		  http.authorizeHttpRequests((request) -> request
				  .requestMatchers("/register" , "/login").permitAll()
				  .anyRequest().authenticated());
		  http.httpBasic(Customizer.withDefaults());
		   http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		   .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		    return http.build();
		 }
	// It is our own authentication provider that helps to connect with database or authenticate the user 
	@Bean
	public AuthenticationProvider authenticationProvider()
	{
	   DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	   provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
	   provider.setUserDetailsService(userDetailsService);
	   
	   
	   return provider;
	 }
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config)  throws Exception{
		
		return config.getAuthenticationManager();
		
	}
	
	// this is handcoded data but we need to reterived data from data bse so we dont need this here we implement a classs that connect and provides these data 
//	@Bean
//    public UserDetailsService userDetailsService()
//    {
//		UserDetails user = User
//				.withDefaultPasswordEncoder()
//				.username("shyam")
//				.password("kelvin")
//				.roles("User")
//				.build();
//		
//		UserDetails user2 = User
//				.withDefaultPasswordEncoder()
//				.username("ram")
//				.password("R@123")
//				.roles("ADMIN")
//				.build();
//		
//		return new InMemoryUserDetailsManager(user , user2);
//    }
	
	
}
