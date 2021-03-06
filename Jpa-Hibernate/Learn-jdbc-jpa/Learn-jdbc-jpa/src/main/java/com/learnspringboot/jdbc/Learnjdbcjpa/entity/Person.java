package com.learnspringboot.jdbc.Learnjdbcjpa.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
// if table name does not matches class, we can add @Table(name="table_name")
@NamedQuery(name = "get_all_persons", query = "select p from Person p")
public class Person {

	@Id // making this the primary key
	@GeneratedValue // auto generate this
	private int id;
	// if column name does not matches field, we can add @Column(name="column_name")
	private String name;
	private String location;
	private Date birthDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birth_date) {
		this.birthDate = birth_date;
	}

	// jpa requires an empty cotr and so does beanpropertyrowmapper
	public Person() {

	}

	public Person(int id, String name, String location, Date birth_date) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.birthDate = birth_date;
	}

	public Person(String name, String location, Date birth_date) {
		super();
		this.name = name;
		this.location = location;
		this.birthDate = birth_date;
	}

	@Override
	public String toString() {
		return "\nPerson [id=" + id + ", name=" + name + ", location=" + location + ", birth_date=" + birthDate + "]";
	}

}
