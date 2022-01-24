package com.jpahibernate.Learnjpahibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.jpahibernate.Learnjpahibernate.LearnJpaHibernateApplication;
import com.jpahibernate.Learnjpahibernate.entity.Course;

@SpringBootTest(classes = LearnJpaHibernateApplication.class)
class NativeQueriesUnitTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	void uT1() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE", Course.class);
		List<?> resultList = query.getResultList();
		logger.info("\nuT1 - SELECT * FROM COURSE -> {}", resultList);
	}

	@Test
	void uT2() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE WHERE ID=?", Course.class);
		query.setParameter(1, 101L); // positional parameter
		List<?> resultList = query.getResultList();
		logger.info("\nuT2 - SELECT * FROM COURSE WHERE ID=? -> {}", resultList);
	}

	@Test
	void uT3() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE WHERE ID=:ID", Course.class);
		query.setParameter("ID", 101L); // named parameter
		List<?> resultList = query.getResultList();
		logger.info("\nuT3 - SELECT * FROM COURSE WHERE ID=:ID -> {}", resultList);
	}

	// Update all rows (update field = last_updated_date_time)
	@Test
	@Transactional
	void uT4() {
		Query query = em.createNativeQuery("UPDATE COURSE SET LAST_UPDATED_DATE_TIME=SYSDATE()", Course.class);
		int rowsAffected = query.executeUpdate();
		logger.info("\nuT4 - UPDATE COURSE SET LAST_UPDATED_DATE_TIME=SYSDATE() -> rows affected = {}", rowsAffected);
	}
}
