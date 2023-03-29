package com.mitraecp.api.mitratests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class MitraTestsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MitraTestsApplication.class, args);
	}

	@GetMapping("/ping")
	public void pingApplication(){
		System.out.println("App is runing");
	}
}
