package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import demo.Exercise;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	private final Exercise exercise = new Exercise();

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		exercise.getAndSetRest();
	}
}
