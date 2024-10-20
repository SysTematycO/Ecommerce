package com.example.demo.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.security.JwtAuthentication;
import com.example.demo.security.JwtUtil;
import com.example.demo.services.CustomLoginServices;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {

	private final Environment environment;

	@Autowired
	private CustomLoginServices customLoginServices;
	
	@Autowired
	private JwtUtil jwtUtil;

	private final String[] getPermitAllEndpoints = { "/login" };

	private final String[] getAuthenticatedEndpoints = { "/user/getAllUsers", "/product/getAllProducts" };

	private final String[] postAuthenticatedEndpoints = { "/user/addUser", "/product/addProduct" };
	
	private final String[] putAuthenticatedEndpoints = { "/user/updateUser", "/product/buyProduct" };
	
	private final String[] deleteAuthenticatedEndpoints = { "/user/deleteUser/**", "/product/deleteProduct/**" };

	public SecurityConfig(Environment environment) {
		this.environment = environment;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
				.cors(cors -> cors.configurationSource(apiConfigurationSource()))
				.authorizeHttpRequests(requests -> requests
						.requestMatchers(HttpMethod.POST, this.getPermitAllEndpoints).permitAll()
						.requestMatchers(HttpMethod.GET, this.getAuthenticatedEndpoints).authenticated()
						.requestMatchers(HttpMethod.POST, this.postAuthenticatedEndpoints).authenticated()
						.requestMatchers(HttpMethod.PUT, this.putAuthenticatedEndpoints).authenticated()
						.requestMatchers(HttpMethod.DELETE, this.deleteAuthenticatedEndpoints).authenticated())
				.addFilterBefore(new JwtAuthentication(this.jwtUtil), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
	
	
	@Bean
	public AuthenticationManager authenticationManager () {
		return new ProviderManager(this.customLoginServices);
	}
	
	CorsConfigurationSource apiConfigurationSource() {

		CorsConfiguration configuration = new CorsConfiguration();

		configuration.setAllowedOrigins(this.getAllowedUrl());
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}


	private List<String> getAllowedUrl() {

		List<String> allowedUrlPartsList = new ArrayList<String>();
		String allowedUrl = Optional.ofNullable(environment.getProperty("spring.urls.extern")).orElse("");

		String allowedUrlParts[] = allowedUrl.split(",");

		for (int i = 0; i < allowedUrlParts.length; i++) {
			allowedUrlPartsList.add(allowedUrlParts[i].trim());
		}

		return allowedUrlPartsList;
	}
}