package com.jpahibernate.Learnjpahibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jpahibernate.Learnjpahibernate.repository.StudentRepository;

@SpringBootApplication
public class LearnJpaHibernateApplication implements CommandLineRunner {

	@Autowired
	StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(LearnJpaHibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		studentRepository.saveStudentWithPassport();
	}

}