package com.highrock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan("com.highrock")
@EnableAutoConfiguration
@MapperScan("com.highrock.mapper")
@EnableDiscoveryClient
public class CraftstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CraftstoreApplication.class, args);
	}
}
