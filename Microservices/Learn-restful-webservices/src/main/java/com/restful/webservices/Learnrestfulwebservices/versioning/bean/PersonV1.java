package com.restful.webservices.Learnrestfulwebservices.versioning.bean;

public class PersonV1 {
	private String name;

	public PersonV1(String name) {
		super();
		this.name = name;
	}

	protected PersonV1() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
