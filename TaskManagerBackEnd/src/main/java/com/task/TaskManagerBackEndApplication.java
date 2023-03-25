package com.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.task.cors.CorsFilter;

@SpringBootApplication
public class TaskManagerBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerBackEndApplication.class, args);
	}
	
	@Bean
	public CorsFilter corsFilter() {
		CorsFilter filter = new CorsFilter();
		return filter;
	}

}
