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
import com.jpahibernate.Learnjpahibernate.entity.Student;

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

	@Test
	// Return courses that does not have students "select * from course where
	// course_id not in (select course_id from student_course)"
	// this but with entities and relationships
	void uT6() {
		TypedQuery<Course> query = em.createQuery("select c from Course c where c.students is empty", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("\nuT6 - select c from Course c where c.student is empty -> {}", resultList);
	}

	// Return courses with atleast 2 students
	@Test
	void uT7() {
		TypedQuery<Course> query = em.createQuery("select c from Course c where size(c.students) >= 2", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("\nuT7 - select c from Course c where size(c.students) >= 2 -> {}", resultList);
	}

	// Return courses order by number of students (default order by is ascending
	// (without "desc"))
	@Test
	void uT8() {
		TypedQuery<Course> query = em.createQuery("select c from Course c order by size(c.students) desc",
				Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("\nuT8 - select c from Course c order by size(c.students) desc -> {}", resultList);
	}

	// return list of students whose passport numbers are like 'pass'
	@Test
	void uT9() {
		TypedQuery<Student> query = em.createQuery("select s from Student s where s.passport.number like '%Pass%'",
				Student.class);
		List<Student> resultList = query.getResultList();
		logger.info("\nuT9 -> {}", resultList);
	}

	// join course, student "Select c,s from Course c join c.students s"
	@Test
	void uT10() {
		Query query = em.createQuery("Select c,s from Course c join c.students s");
		List<Object[]> resultList = query.getResultList();
		for (Object[] result : resultList) {
			logger.info("Course {} Student {}", result[0], result[1]);
		}
	}

	// left join course, student "Select c,s from Course c left join c.students s"
	@Test
	void uT11() {
		Query query = em.createQuery("Select c,s from Course c left join c.students s");
		List<Object[]> resultList = query.getResultList();
		for (Object[] result : resultList) {
			logger.info("Course {} Student {}", result[0], result[1]);
		}
	}

	// cross join course, student "select c,s from Course c, Student s"
	@Test
	void uT12() {
		Query query = em.createQuery("select c,s from Course c, Student s");
		List<Object[]> resultList = query.getResultList();
		for (Object[] result : resultList) {
			logger.info("Course {} Student {}", result[0], result[1]);
		}
	}
}
