package com.restful.webservices.Learnrestfulwebservices.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@Size(min = 2, message = "Name cannot be less 3 characters.") // min size = 2 with error message on failed
																	// validation
	@Column(nullable = false)
	private String name;

	@Past(message = "Birthdate should always be in past.") // birthdate will always be in past with error message on
															// failed validation
	private Date birthDate;

	@OneToMany(mappedBy = "user")
	private List<Post> posts;

	protected User() {
	}

	public User(String name, Date birthDate) {
		super();
		this.name = name;
		this.birthDate = birthDate;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void addPost(Post post) {
		this.posts.add(post);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
}
