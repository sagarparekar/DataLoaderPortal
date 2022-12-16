package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.example.demo.JwtFilter.SecurityFilter;

@SpringBootApplication
public class CompanyManagmentAppApplication {

/*

	@Bean
	public FilterRegistrationBean jwtFilter() {
		FilterRegistrationBean obj = new FilterRegistrationBean();
		obj.setFilter(new SecurityFilter());
		obj.addUrlPatterns("/api/*");
		return obj;
	}

*/
	public static void main(String[] args) {
		SpringApplication.run(CompanyManagmentAppApplication.class, args);
	}

}
