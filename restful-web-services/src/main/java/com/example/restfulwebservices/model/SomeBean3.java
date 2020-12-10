package com.example.restfulwebservices.model;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter(value = "SomeBeanFilter")
public class SomeBean3 {

	private String f1;
	private String f2;
	private String f3;
	
	public SomeBean3() {}
	
	public SomeBean3(String f1, String f2, String f3) {
		super();
		this.f1 = f1;
		this.f2 = f2;
		this.f3 = f3;
	}

	/**
	 * @return the f1
	 */
	public String getF1() {
		return f1;
	}

	/**
	 * @param f1 the f1 to set
	 */
	public void setF1(String f1) {
		this.f1 = f1;
	}

	/**
	 * @return the f2
	 */
	public String getF2() {
		return f2;
	}

	/**
	 * @param f2 the f2 to set
	 */
	public void setF2(String f2) {
		this.f2 = f2;
	}

	/**
	 * @return the f3
	 */
	public String getF3() {
		return f3;
	}

	/**
	 * @param f3 the f3 to set
	 */
	public void setF3(String f3) {
		this.f3 = f3;
	}
}
