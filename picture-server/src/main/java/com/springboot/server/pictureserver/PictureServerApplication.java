package com.springboot.server.pictureserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class PictureServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PictureServerApplication.class, args);
	}
}
