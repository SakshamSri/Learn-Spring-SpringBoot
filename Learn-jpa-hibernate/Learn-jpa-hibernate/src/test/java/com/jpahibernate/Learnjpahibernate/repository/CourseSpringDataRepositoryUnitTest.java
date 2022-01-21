package com.jpahibernate.Learnjpahibernate.repository;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.jpahibernate.Learnjpahibernate.LearnJpaHibernateApplication;
import com.jpahibernate.Learnjpahibernate.entity.Course;

@SpringBootTest(classes = LearnJpaHibernateApplication.class)
class CourseSpringDataRepositoryUnitTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseSpringDataRepository csdp;

	@Test
	// verify a course with id 101 is present
	void uT1() {
		Optional<Course> optional = csdp.findById(101L);
		Assertions.assertTrue(optional.isPresent());
	}

	@Test
	// verify a course with id 109 is not present
	void uT2() {
		Optional<Course> optional = csdp.findById(109L);
		Assertions.assertFalse(optional.isPresent());
	}

	@Test
	// checking different different functions offered by spring data repository
	void uT3() {
		Course course = csdp.save(new Course("newCourse"));
		course.setName("newCourseUpdated");
		csdp.save(course);

		logger.info("Count -> {}", csdp.count());
		logger.info("Courses -> {}", csdp.findAll());
	}

	@Test
	// findAll with Sort
	void uT4() {
		Sort sort = Sort.by(Sort.Direction.DESC, "name");
		logger.info("Sort DESC Courses -> {}", csdp.findAll(sort));

		// if need to add another criteria for sort (what to do if name is same)
		sort = Sort.by(Sort.Direction.DESC, "name").and(Sort.by(Sort.Direction.ASC, "createdDateTime"));
		logger.info("Sort DESC Courses + ASC order of createdDateTime (if same name) -> {}", csdp.findAll(sort));
	}

	@Test
	// add pagination (3 data per page)
	void uT5() {
		// create a request for page size=3, and starting index=0
		PageRequest request = PageRequest.of(0, 3);

		// now findall(request) will return first page
		Page<Course> firstPage = csdp.findAll(request);
		if (firstPage.hasContent())
			logger.info("First Page -> {}", firstPage.getContent());

		// we can check if more data is there (or user asks for more data) from the
		// firstPage object using hasNext or isPageable
		boolean hasNext = firstPage.hasNext();
		if (hasNext) {
			Pageable secondPageable = firstPage.nextPageable();
			Page<Course> secondPage = csdp.findAll(secondPageable);
			if (secondPage.hasContent())
				logger.info("Second Page -> {}", secondPage.getContent());
		}
	}

	@Test
	// use custom function defined findByName
	void uT6() {
		logger.info("Courses with name 'Course101' -> {}", csdp.findByName("Course101"));
	}
}
