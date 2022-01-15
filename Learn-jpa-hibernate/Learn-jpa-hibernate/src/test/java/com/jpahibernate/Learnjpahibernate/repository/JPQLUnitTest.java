package com.jpahibernate.Learnjpahibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jpahibernate.Learnjpahibernate.LearnJpaHibernateApplication;
import com.jpahibernate.Learnjpahibernate.entity.Course;

@SpringBootTest(classes = LearnJpaHibernateApplication.class)
class JPQLUnitTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	void uT1() {
		Query query = em.createQuery("Select c from Course c");
		List<?> resultList = query.getResultList();
		logger.info("\nuT1 - Select c from Course c -> {}", resultList);
	}

	// UT2 is better because using TypedQuery is better.
	@Test
	void uT2() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("\nuT2 - Select c from Course c -> {}", resultList);
	}

	@Test
	void uT3() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c where name like '%100 Steps'", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("\nuT3 - Select c from Course c where name like '%Course'-> {}", resultList);
	}

	@Test
	void uT4() {
		TypedQuery<Course> query = em.createNamedQuery("get_all_courses", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("\nuT4 - get_all_courses -> {}", resultList);
	}

	@Test
	void uT5() {
		TypedQuery<Course> query = em.createNamedQuery("get_all_100_steps_courses", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("\nuT5 - get_all_100_steps_courses -> {}", resultList);
	}
}
