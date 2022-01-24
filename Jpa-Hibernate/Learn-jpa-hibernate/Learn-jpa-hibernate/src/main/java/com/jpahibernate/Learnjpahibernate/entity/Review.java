package com.jpahibernate.Learnjpahibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Review {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	@Enumerated(value = EnumType.STRING) // default is ordinal, with ordinal we can insert using indexes but if enum
											// positioning changes the value that index corresponds to will also change

	private ReviewRating rating;

	private String description;

	@ManyToOne // default fetchType is Eager
	private Course course;

	protected Review() {
	}

	public Review(ReviewRating rating) {
		super();
		this.rating = rating;
	}

	public Review(ReviewRating rating, String description) {
		super();
		this.rating = rating;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ReviewRating getRating() {
		return rating;
	}

	public void setRating(ReviewRating rating) {
		this.rating = rating;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "\nReview [id=" + id + ", rating=" + rating + ", description=" + description + "]";
	}

}
