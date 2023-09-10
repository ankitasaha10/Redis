package com.example.redisFirstProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisFirstProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisFirstProjectApplication.class, args);
	}

}
