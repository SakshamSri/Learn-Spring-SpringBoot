package com.jpahibernate.Learnjpahibernate.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jpahibernate.Learnjpahibernate.LearnJpaHibernateApplication;
import com.jpahibernate.Learnjpahibernate.entity.Course;

@SpringBootTest(classes = LearnJpaHibernateApplication.class)
class CriteriaQueryUnitTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	/*
	 * Steps: 1) Use Criteria Builder to create a Criteria Query returning the
	 * expected result object 2) Define roots for tables which are involved in the
	 * Query (which table you are getting the data from) 3) Define Predicates etc
	 * using Criteria Builder 4) Add Predicates etc to the Criteria Query 5) Build
	 * the TypedQuery using the entity manager and criteria query
	 */

	// Criteria query for : "Select c from Course c"
	@Test
	void uT1() {

		// Step 1
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class); // expected result is Course class type

		// Step 2
		Root<Course> from = cq.from(Course.class); // doing a FROM COURSE class type

		// Step 3 and Step 4 are not needed because we are not doing anything else like
		// order by, like, join

		// Step 5
		TypedQuery<Course> query = em.createQuery(cq.select(from));

		// Get the result
		logger.info("uT1 : Result -> {}", query.getResultList());
	}

	// Criteria query for : "Select c from Course c where name like '%100 Steps"
	@Test
	void uT2() {

		// Step 1
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class); // expected result is Course class type

		// Step 2
		Root<Course> from = cq.from(Course.class); // doing a FROM COURSE class type

		// Step 3
		Predicate like = cb.like(from.get("name"), "%100 Steps");

		// Step 4
		cq.where(like);

		// Step 5
		TypedQuery<Course> query = em.createQuery(cq.select(from));

		// Get the result
		logger.info("uT2 : Result -> {}", query.getResultList());
	}

	// Criteria query for : "Select c from Course c where c.students is Empty"
	@Test
	void uT3() {

		// Step 1
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class); // expected result is Course class type

		// Step 2
		Root<Course> from = cq.from(Course.class); // doing a FROM COURSE class type

		// Step 3
		Predicate pred = cb.isEmpty(from.get("students"));

		// Step 4
		cq.where(pred);

		// Step 5
		TypedQuery<Course> query = em.createQuery(cq.select(from));

		// Get the result
		logger.info("uT3 : Result -> {}", query.getResultList());
	}

	// Criteria query for : "Select c,s from Course c join c.students s"
	@Test
	void uT4() {

		// Step 1
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class); // expected result is Course class type

		// Step 2
		Root<Course> from = cq.from(Course.class); // doing a FROM COURSE class type

		// Step 3
		from.join("students");

		// Step 4

		// Step 5
		TypedQuery<Course> query = em.createQuery(cq.select(from));

		// Get the result
		logger.info("uT4 : Result -> {}", query.getResultList());
	}

	// Criteria query for : "Select c,s from Course c left join c.students s"
	@Test
	void uT5() {

		// Step 1
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class); // expected result is Course class type

		// Step 2
		Root<Course> from = cq.from(Course.class); // doing a FROM COURSE class type

		// Step 3
		from.join("students", JoinType.LEFT);

		// Step 4

		// Step 5
		TypedQuery<Course> query = em.createQuery(cq.select(from));

		// Get the result
		logger.info("uT5 : Result -> {}", query.getResultList());
	}
}
