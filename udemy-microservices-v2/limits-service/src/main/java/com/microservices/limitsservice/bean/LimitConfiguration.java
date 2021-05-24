package com.microservices.limitsservice.bean;

public class LimitConfiguration {

	private int minimum;
	private int maximum;
	
	public LimitConfiguration() {}

	public LimitConfiguration(int maximum, int minimum) {
		super();
		this.minimum = minimum;
		this.maximum = maximum;
	}

	/**
	 * @return the minimum
	 */
	public int getMinimum() {
		return minimum;
	}

	/**
	 * @param minimum the minimum to set
	 */
	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	/**
	 * @return the maximum
	 */
	public int getMaximum() {
		return maximum;
	}

	/**
	 * @param maximum the maximum to set
	 */
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	
	
}
