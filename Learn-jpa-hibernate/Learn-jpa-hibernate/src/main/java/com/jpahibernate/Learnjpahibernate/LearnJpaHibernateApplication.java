package com.jpahibernate.Learnjpahibernate;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jpahibernate.Learnjpahibernate.entity.FullTimeEmployee;
import com.jpahibernate.Learnjpahibernate.entity.PartTimeEmployee;
import com.jpahibernate.Learnjpahibernate.repository.CourseRepository;
import com.jpahibernate.Learnjpahibernate.repository.EmployeeRepository;
import com.jpahibernate.Learnjpahibernate.repository.StudentRepository;

@SpringBootApplication
public class LearnJpaHibernateApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(LearnJpaHibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// studentRepository.saveStudentWithPassport();
		// courseRepository.saveReviewsForCourse(103L);
		// studentRepository.saveStudentWithCourseHardcoded();

		employeeRepository.saveEmployee(new FullTimeEmployee("Full10001", new BigDecimal("10001")));
		employeeRepository.saveEmployee(new PartTimeEmployee("Part11", new BigDecimal("11")));

		// logger.info("\n employeeRepository.getEmployees() -> {}",
		// employeeRepository.getEmployees());
		logger.info("\n employeeRepository.getFullTimeEmployees() -> {}", employeeRepository.getFullTimeEmployees());
		logger.info("\n employeeRepository.getPartTimeEmployees() -> {}", employeeRepository.getPartTimeEmployees());
	}

}
