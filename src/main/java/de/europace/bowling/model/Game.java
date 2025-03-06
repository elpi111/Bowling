package de.europace.bowling.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.europace.bowling.enumeration.Type;

public class Game {
	
	private static final Logger logger = LoggerFactory.getLogger(Game.class);
	
	private List<Frame> frames = new ArrayList<>();
	
	public Game(String gameString) {
		List<String> frameStrings = Arrays.asList(gameString.split("\\|\\|"));
		
		IntStream.range(0, frameStrings.size()).forEach(index -> { 
			this.addFrame(index + 1, frameStrings.get(index));
		});
	}

	public List<Frame> getFrames() {
		return frames;
	}

	public void setFrames(List<Frame> frames) {
		this.frames = frames;
	}
	
	public void addFrame(int idx, String frameString) {
		Frame frame = new Frame();
		
		frame.setFrameNumber(idx);
		
		logger.info("Frame: " + frameString);
		
		List<String> rollStrings = Arrays.asList(frameString.split(":"));
		
		IntStream.range(0, rollStrings.size()).forEach(index -> { 
			frame.addRoll(index, rollStrings.get(index));
		});
			
		frames.add(frame);
	}
	
	public int calculateResult() {
		int resultOfGame = 0;
		
		for (Frame frame : frames) {
			int frameNumber = frame.getFrameNumber();
			int result = frame.getRolls().stream().mapToInt(r -> r.getNumberOfKnockedDownPins()).sum();
			
			if (frameNumber > 1) {
				int predecessorArrayIndex = frameNumber - 2;
				result += frames.get(predecessorArrayIndex).getResult(); 
			}
			
			if (frame.getType().equals(Type.STRIKE)) {
				if (frameNumber < 10) {
					int successorArrayIndex = frameNumber;
					result += frames.get(successorArrayIndex).getRolls().stream().mapToInt(r -> {
						if (r.getRollNumber() < 3) {
							return r.getNumberOfKnockedDownPins();
						} else {
							return 0;
							}
					}).sum();
					
					if (frames.get(successorArrayIndex).getType().equals(Type.STRIKE) &&
							frameNumber < 9) {
						result += frames.get(successorArrayIndex + 1).getRolls().stream().mapToInt(r -> {
							if (r.getRollNumber() == 1) {
								return r.getNumberOfKnockedDownPins();
							} else {
								return 0;
								}
						}).sum();
					}
				}
			}
			
			if (frame.getType().equals(Type.SPARE)) {
				if (frameNumber < 10)
					result += frames.get(frameNumber).getRolls().get(0).getNumberOfKnockedDownPins();
			}
			
			frame.setResult(result);
		}
		
		resultOfGame = frames.get(9).getResult();
		
		return resultOfGame;
	}

}
