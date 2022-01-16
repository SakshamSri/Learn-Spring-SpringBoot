package com.jpahibernate.Learnjpahibernate.repository;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.jpahibernate.Learnjpahibernate.LearnJpaHibernateApplication;
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
}
