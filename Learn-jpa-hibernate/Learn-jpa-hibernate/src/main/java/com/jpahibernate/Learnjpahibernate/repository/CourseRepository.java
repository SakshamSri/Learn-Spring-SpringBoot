package com.jpahibernate.Learnjpahibernate.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jpahibernate.Learnjpahibernate.entity.Course;

@Repository
@Transactional
public class CourseRepository {

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
}
