package com.jpahibernate.Learnjpahibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jpahibernate.Learnjpahibernate.entity.Course;
import com.jpahibernate.Learnjpahibernate.repository.CourseRepository;

@SpringBootApplication
public class LearnJpaHibernateApplication implements CommandLineRunner {

	@Autowired
	private CourseRepository courseRepository;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(LearnJpaHibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("\nCourse with id=101 -> {}", courseRepository.findCourseById(101L));
		courseRepository.deleteCourseById(102L);
		logger.info("\nCourse with id=104 added -> {}", courseRepository.saveCourse(new Course("Course104")));
		logger.info("\nCourse with id=102 added -> {}", courseRepository.saveCourse(new Course("Course102")));
		courseRepository.entityManagerStuffsAgain();
	}

}
