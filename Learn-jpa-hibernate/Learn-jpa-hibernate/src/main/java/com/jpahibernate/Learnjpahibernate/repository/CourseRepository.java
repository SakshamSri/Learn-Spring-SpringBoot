package com.jpahibernate.Learnjpahibernate.repository;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jpahibernate.Learnjpahibernate.entity.Course;
import com.jpahibernate.Learnjpahibernate.entity.Review;
import com.jpahibernate.Learnjpahibernate.entity.ReviewRating;

@Repository
@Transactional
public class CourseRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	EntityManager entityManager;

	public Course findCourseById(Long id) {
		return entityManager.find(Course.class, id);
	}

	public Course saveCourse(Course course) { // insert + update
		if (course.getId() == null)
			entityManager.persist(course); // insert
		else
			entityManager.merge(course); // update
		return course;
	}

	public void deleteCourseById(Long id) {
		Course findCourse = findCourseById(id);
		if (findCourse != null)
			entityManager.remove(findCourse);
	}

	public void entityManagerStuffs() {
		Course course1 = new Course("CourseA");
		entityManager.persist(course1);

		Course course2 = new Course("CourseB");
		entityManager.persist(course2);

		entityManager.flush(); // updates the db

		course1.setName("CourseAA");
		course2.setName("CourseBB");
		// entityManager.clear(); - no courses persists now
		// entityManager.detach(course2); - course2 not persisted
		entityManager.refresh(course1); // refresh entity course1 with values from db (so CourseAA update is lost now)
		entityManager.flush();
	}

	public void entityManagerStuffsAgain() {
		Course course1 = new Course("CourseA");
		entityManager.persist(course1);

		Course course2 = findCourseById(101L);
		course2.setName("Course101_updated");
	}

	public void saveReviewsForCourse(long id) {
		Course course = findCourseById(id);
		if (course == null)
			return;
		logger.info("\nCourse reviews -> {}", course.getReviews());

		Review review1 = new Review(ReviewRating.FIVE, "DescriptionNew101");
		Review review2 = new Review(ReviewRating.FOUR, "DescriptionNew102");

		/**
		 * entityManager.persist(review1); entityManager.persist(review2); if we persist
		 * before setting up the relationship, then first insert into review is made
		 * with blank course id and then an update review is sent to update the course
		 * id
		 */
		course.addReview(review1);
		review1.setCourse(course);

		course.addReview(review2);
		review2.setCourse(course);

		entityManager.persist(review1);
		entityManager.persist(review2);

	}

	public void saveReviewsForCourse(long id, ArrayList<Review> reviews) {
		Course course = findCourseById(id);
		if (course == null)
			return;
		logger.info("\nCourse reviews -> {}", course.getReviews());

		for (Review review : reviews) {
			course.addReview(review);
			review.setCourse(course);
			entityManager.persist(review);
		}

	}
}
