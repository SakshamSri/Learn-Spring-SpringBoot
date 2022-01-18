package com.jpahibernate.Learnjpahibernate.repository;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import com.jpahibernate.Learnjpahibernate.LearnJpaHibernateApplication;
import com.jpahibernate.Learnjpahibernate.entity.Course;
import com.jpahibernate.Learnjpahibernate.entity.Passport;
import com.jpahibernate.Learnjpahibernate.entity.Student;

@SpringBootTest(classes = LearnJpaHibernateApplication.class)
class StudentRepositoryUnitTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentRepository studentRepository;
	@Autowired
	EntityManager em;

	// retrieve student & passport details
	@Test
	@Transactional
	public void uT1() {
		Student find = em.find(Student.class, 201L);
		logger.info("\nStudent info -> {}", find);
		logger.info("\nPassport info -> {}", find.getPassport());
	}

	@Test
	@DirtiesContext
	public void ut2() {
		studentRepository.someFunction();
	}

	// retrieve student details from passport
	@Test
	@Transactional
	public void uT3() {
		Passport passport = em.find(Passport.class, 302L);

		logger.info("\nPassport details -> {}", passport);

		logger.info("\nStudent details -> {}", passport.getStudent());
	}

	@Test
	@Transactional
	public void uT4() {
		Student student = em.find(Student.class, 201L);

		logger.info("\nStudent details -> {}", student);

		logger.info("\nCourse details -> {}", student.getCourses());
	}

	@Test
	@Transactional
	public void uT5() {
		Course course = em.find(Course.class, 101L);

		logger.info("\nCourse details -> {}", course);

		logger.info("\nStudent details -> {}", course.getStudents());
	}
}
