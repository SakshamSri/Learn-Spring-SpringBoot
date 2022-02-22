package com.restful.webservices.Learnrestfulwebservices.versioning.bean;

public class PersonV2 {
	private Name name;

	public PersonV2(Name name) {
		super();
		this.name = name;
	}

	protected PersonV2() {
		super();
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

}
