package com.jpahibernate.Learnjpahibernate.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.jpahibernate.Learnjpahibernate.LearnJpaHibernateApplication;
import com.jpahibernate.Learnjpahibernate.entity.Course;

@SpringBootTest(classes = LearnJpaHibernateApplication.class)
class CourseRepositoryUnitTest {

	@Autowired
	CourseRepository courseRepository;

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

}
