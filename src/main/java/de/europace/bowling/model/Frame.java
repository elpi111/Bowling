package de.europace.bowling.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.europace.bowling.enumeration.Type;

public class Frame {
	
	private static final Logger logger = LoggerFactory.getLogger(Frame.class);
	
	private List<Roll> rolls = new ArrayList<>();
	
	private Type type;
	
	private Integer frameNumber;
	
	private Integer result;

	public List<Roll> getRolls() {
		return rolls;
	}

	public void setRolls(List<Roll> rolls) {
		this.rolls = rolls;
	}
	
	public void addRoll(int idx, String rollString) {
		Roll roll = new Roll();
		
		roll.setRollNumber(idx + 1);
		
		//logger.info("Roll: " + rollString);
		
		switch(rollString) {
			case "X":
				roll.setNumberOfKnockedDownPins(10);
				if (roll.getRollNumber() < 3)
					this.setType(type.STRIKE);
				break;
			case "/":
				int numberOfKnockedDownPinsInPreviousRoll = getRolls().get(idx - 1).getNumberOfKnockedDownPins();
				roll.setNumberOfKnockedDownPins(10 - numberOfKnockedDownPinsInPreviousRoll);
				if (roll.getRollNumber() < 3)
					this.setType(type.SPARE);
				break;
			default:
				roll.setNumberOfKnockedDownPins(Integer.valueOf(rollString));
				if (roll.getRollNumber() < 3)
					this.setType(type.STANDARD);
		}
			
		rolls.add(roll);
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Integer getFrameNumber() {
		return frameNumber;
	}

	public void setFrameNumber(Integer frameNumber) {
		this.frameNumber = frameNumber;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	
	
}
