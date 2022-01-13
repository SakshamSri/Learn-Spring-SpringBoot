package com.jpahibernate.Learnjpahibernate.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jpahibernate.Learnjpahibernate.entity.Course;

@Repository
public class CourseRepository {

	@Autowired
	EntityManager entityManager;

	public Course findCourseById(Long id) {
		return entityManager.find(Course.class, id);
	}

	public Course saveCourse(Course course) { // insert + update
		return entityManager.merge(course);
	}

	public void deleteCourseById(Long id) {
		Course findCourse = findCourseById(id);
		if (findCourse != null)
			entityManager.remove(findCourse);
	}
}
