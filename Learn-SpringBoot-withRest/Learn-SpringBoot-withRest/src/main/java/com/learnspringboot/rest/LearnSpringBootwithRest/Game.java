package com.learnspringboot.rest.LearnSpringBootwithRest;

public class Game {

	private long id;
	private String name;
	private String company;

	public Game(long id, String name, String company) {
		super();
		this.id = id;
		this.name = name;
		this.company = company;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCompany() {
		return company;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", company=" + company + "]";
	}

}
