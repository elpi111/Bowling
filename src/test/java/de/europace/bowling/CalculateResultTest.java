package de.europace.bowling;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

import de.europace.bowling.model.Game;
import de.europace.bowling.service.FileService;

class CalculateResultTest {
	
	private FileService fileService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		fileService = new FileService();
		List<String> lines = new ArrayList<>();
		
		lines.add("1:4||4:5||6:/||5:/||X||0:1||7:/||6:/||X||2:/:6");
		lines.add("1:0||4:5||6:/||5:/||X||0:1||7:/||6:/||X||2:0");
		lines.add("1:0||4:5||6:/||5:/||X||X||7:/||6:/||X||2:0");
		lines.add("X||X||X||X||X||X||X||X||X||X:X:X||");
		lines.add("9:0||9:0||9:0||9:0||9:0||9:0||9:0||9:0||9:0||9:0||");
		
		fileService.init(lines);
	}

	@Test
	void test() {
		
		Game game = fileService.getNextGame();	
		int result = game.calculateResult();
		int expected = 133;
		Assert.assertEquals(expected, result);
		
		game = fileService.getNextGame();	
		result = game.calculateResult();
		expected = 107;
		Assert.assertEquals(expected, result);
		
		game = fileService.getNextGame();	
		result = game.calculateResult();
		expected = 142;
		Assert.assertEquals(expected, result);
		
		game = fileService.getNextGame();	
		result = game.calculateResult();
		expected = 300;
		Assert.assertEquals(expected, result);
		
		game = fileService.getNextGame();	
		result = game.calculateResult();
		expected = 90;
		Assert.assertEquals(expected, result);
	}

}
