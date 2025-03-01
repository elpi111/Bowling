package de.europace.bowling.model;

import org.springframework.util.Assert;

public class Roll {
	
	private Integer rollNumber;
	
	private Integer numberOfKnockedDownPins;

	public Integer getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(Integer rollNumber) {
		Assert.isTrue(rollNumber >= 1, "rollNumber has to be >= 1");
		Assert.isTrue(rollNumber <= 3, "rollNumber has to be <= 3");
		
		this.rollNumber = rollNumber;
	}

	public Integer getNumberOfKnockedDownPins() {
		return numberOfKnockedDownPins;
	}

	public void setNumberOfKnockedDownPins(Integer numberOfKnockedDownPins) {
		Assert.isTrue(numberOfKnockedDownPins >= 0, "numberOfKnockedDownPins has to be >= 0");
		Assert.isTrue(numberOfKnockedDownPins <= 10, "numberOfKnockedDownPins has to be <= 10");
		
		this.numberOfKnockedDownPins = numberOfKnockedDownPins;
	}
	
	
}
