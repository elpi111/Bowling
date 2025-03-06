package de.europace.bowling.service;

/**
 * The input file contains the data for one game in a single line.
 * So each line is a game.
 * Each game consists of 10 frames.
 * Frames are seperated by "||".
 * Each frame contains 2 rolls, the last frame could contain 3 rolls.
 * Rolls are separated by ":".
 * A strike is defined by "X".
 * A spare is defined by "/"
 * 
 * Example:
 * 
 * 1:4||4:5||6:/||5:/||X||0:1||7:/||6:/||X||2:/:6
 */


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import de.europace.bowling.config.AppConfig;
import de.europace.bowling.model.Game;

@Component
public class FileService {
	
	private static final Logger logger = LoggerFactory.getLogger(FileService.class);
	
	private AppConfig appConfig;
	
	private List<String> lines;
	
	private Iterator<String> iterator;
	
	public FileService() {
		
	}
	
	public FileService(AppConfig appConfig) {
		this.appConfig = appConfig;
		
		try {
            lines = Files.readAllLines(Paths.get(this.appConfig.filePath));
            iterator = lines.iterator();
            
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            
        }
	}
	
	public void init(List<String> lines) {
		this.lines = lines;
		iterator = lines.iterator();
	}
	
	public Game getNextGame() {
		Game game = null;
		
		if (iterator.hasNext()) {
			String gameString = iterator.next();
			logger.info("Game: " + gameString);
			
			game = new Game(gameString);
			
		}
		
		return game;
	}
	
	public boolean hasNextGame() {
		return iterator.hasNext();
	}
}
