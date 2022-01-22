package com.jpahibernate.Learnjpahibernate.repository;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import com.jpahibernate.Learnjpahibernate.LearnJpaHibernateApplication;
import com.jpahibernate.Learnjpahibernate.entity.Course;
import com.jpahibernate.Learnjpahibernate.entity.Review;

@SpringBootTest(classes = LearnJpaHibernateApplication.class)
class CourseRepositoryUnitTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	EntityManager em;

	@Test
	void findCourseByIdUT() {
		Course course = courseRepository.findCourseById(101L);
		Assertions.assertEquals("Course101", course.getName());
	}

	@Test
	@DirtiesContext
	void deleteCourseByIdUT() {
		courseRepository.deleteCourseById(102L);
		Assertions.assertNull(courseRepository.findCourseById(102L));
	}

	@Test
	@DirtiesContext
	void saveCourseByIdUT() {
		// Insert verification
		Course course = courseRepository.saveCourse(new Course("Course105"));
		Assertions.assertEquals("Course105", course.getName());

		// Update verification
		course = courseRepository.findCourseById(101L);
		Assertions.assertEquals("Course101", course.getName());
		course.setName("Course101X");
		courseRepository.saveCourse(course);
		Course courseFound = courseRepository.findCourseById(101L);
		Assertions.assertEquals("Course101X", courseFound.getName());
	}

	@Test
	@Transactional // Course->Review is OneToMany and default fetchType of OneToMany is Lazy so we
					// need @Transactional because
	// reviews data wont be prefetched
	void uT1() {
		Course course = courseRepository.findCourseById(101L);
		logger.info("\nuT1 - course.getReviews() -> {}", course.getReviews());
	}

	@Test
	// @Transactional is not needed because by default ManyToOne is Eager fetchType.
	void uT2() {
		Review review = em.find(Review.class, 401L);
		logger.info("\nuT2 - review.getCourse() -> {}", review.getCourse());
	}

	@Test
	@Transactional // the whole section needs to be within a transaction
	// verifying first level cache is present in PersistenceContext
	void uT3() {
		Course course1 = courseRepository.findCourseById(101L);
		logger.info("First retrieval of course with id 101 - {}", course1);

		Course course2 = courseRepository.findCourseById(101L); // when checked from logs, we can see no other select
																// query is being ran, the data is fetched instantly
		logger.info("Second retrieval of course with id 101 - {}", course2);
	}
}
