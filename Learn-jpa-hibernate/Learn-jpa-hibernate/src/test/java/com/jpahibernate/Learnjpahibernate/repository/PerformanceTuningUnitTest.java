package com.jpahibernate.Learnjpahibernate.repository;

import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.jpahibernate.Learnjpahibernate.LearnJpaHibernateApplication;
import com.jpahibernate.Learnjpahibernate.entity.Course;

@SpringBootTest(classes = LearnJpaHibernateApplication.class)
class PerformanceTuningUnitTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	EntityManager em;

	// N+1 problem (1 query to get all courses and n queries to get all students
	// related to each course)
	@Test
	@Transactional
	void uT1() {
		List<Course> resultList = em.createNamedQuery("get_all_courses", Course.class).getResultList();
		for (Course course : resultList)
			logger.info("Course {}  Student {}", course, course.getStudents());
	}

	// Solution to N+1 problem using EntityGraph
	@Test
	@Transactional
	void uT2() {
		EntityGraph<Course> createEntityGraph = em.createEntityGraph(Course.class);
		Subgraph<Object> addSubgraph = createEntityGraph.addSubgraph("students");

		List<Course> resultList = em.createNamedQuery("get_all_courses", Course.class)
				.setHint("javax.persistence.loadgraph", createEntityGraph).getResultList();

		for (Course course : resultList)
			logger.info("Course {}  Student {}", course, course.getStudents());
	}

	// Solution to N+1 problem using JoinFetch
	@Test
	@Transactional
	void uT3() {
		List<Course> resultList = em.createNamedQuery("get_all_courses_join_fetch", Course.class).getResultList();

		for (Course course : resultList)
			logger.info("Course {}  Student {}", course, course.getStudents());
	}
}
