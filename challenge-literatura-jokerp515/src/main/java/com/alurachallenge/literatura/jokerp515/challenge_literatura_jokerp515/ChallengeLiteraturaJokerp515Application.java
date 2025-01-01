package com.alurachallenge.literatura.jokerp515.challenge_literatura_jokerp515;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alurachallenge.literatura.jokerp515.challenge_literatura_jokerp515.main.Main;

@SpringBootApplication
public class ChallengeLiteraturaJokerp515Application implements CommandLineRunner {

	@Autowired
	private Main main;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiteraturaJokerp515Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		main.menu();
	}

}
