package de.europace.bowling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.europace.bowling.model.Game;
import de.europace.bowling.service.FileService;

@SpringBootApplication
public class Bowling implements CommandLineRunner {
	
	@Autowired
	private FileService fileService;

	public static void main(String[] args) {
		SpringApplication.run(Bowling.class, args);
	}
	
	public void run(String[] args) {
		Game game = fileService.getNextGame();	
		int result = game.calculateResult();
		System.out.println("Result: " + result);
		
		game = fileService.getNextGame();	
		result = game.calculateResult();
		System.out.println("Result: " + result);
	}

}
