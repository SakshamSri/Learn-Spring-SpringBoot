package com.jpahibernate.Learnjpahibernate.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQueries(value = { @NamedQuery(name = "get_all_courses", query = "Select c from Course c"),
		@NamedQuery(name = "get_all_100_steps_courses", query = "Select c from Course c where name like '%100 Steps'"),
		@NamedQuery(name = "get_all_courses_join_fetch", query = "Select c from Course c JOIN FETCH c.students s") })
// @NamedQuery(name = "get_all_courses", query = "Select c from Course c")
// @Table(name = "CourseDetails")
@Cacheable // courses when fetched will be first checked in L2C and if not there updation
			// of L2C is done. So, next time same details are fetched from L2C
@SQLDelete(sql = "update course set is_deleted=true where id=?") // when delete is done on Course table, this query will
																	// be called instead of actual delete
@Where(clause = "is_deleted=false") // specifying that only when is_deleted is false, then only we will get that row
									// details otherwise we skip
public class Course {

	@Id
	@GeneratedValue
	private Long id;

	// @Column(name = "FullName", nullable = false)
	private String name;

	@OneToMany(mappedBy = "course") // default fetchType is Lazy
	private List<Review> reviews = new ArrayList<>();

	@ManyToMany(mappedBy = "courses")
	@JsonIgnore // we add this because courses is exposed via rest and when requested students
				// contains passport and then passport contains students so this gets stuck in
				// an infinite loop
	private List<Student> students = new ArrayList<>();

	@UpdateTimestamp
	private LocalDateTime lastUpdatedDateTime;

	@CreationTimestamp
	private LocalDateTime createdDateTime;

	private boolean isDeleted;

	protected Course() {
	}

	public Course(String name) {
		super();
		this.name = name;
	}

	@PreRemove
	private void preRemove() {
		this.isDeleted = true; // setting is_deleted to true before any removal is done for the entity so that
								// cache can be updated and then softdelete will update the database also (now
								// both cache and db are in sync)
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void addReview(Review review) {
		this.reviews.add(review);
	}

	public void removeReview(Review review) {
		this.reviews.remove(review);
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}

	public void removeStudent(Student student) {
		this.students.remove(student);
	}

	public List<Student> getStudents() {
		return students;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + "]";
	}

}
