package com.jpahibernate.Learnjpahibernate.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@NamedQueries(value = { @NamedQuery(name = "get_all_courses", query = "Select c from Course c"),
		@NamedQuery(name = "get_all_100_steps_courses", query = "Select c from Course c where name like '%100 Steps'") })
// @NamedQuery(name = "get_all_courses", query = "Select c from Course c")
// @Table(name = "CourseDetails")
public class Course {

	@Id
	@GeneratedValue
	private Long id;

	// @Column(name = "FullName", nullable = false)
	private String name;

	@UpdateTimestamp
	private LocalDateTime lastUpdatedDateTime;

	@CreationTimestamp
	private LocalDateTime createdDateTime;

	protected Course() {
	}

	public Course(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "\nCourse [id=" + id + ", name=" + name + "]";
	}

}
