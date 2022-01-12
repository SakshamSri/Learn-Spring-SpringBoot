package com.learnspringboot.jdbc.Learnjdbcjpa;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.learnspringboot.jdbc.Learnjdbcjpa.entity.Person;
import com.learnspringboot.jdbc.Learnjdbcjpa.jdbc.PersonJdbcDao;

@SpringBootApplication
public class LearnJdbcJpaApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PersonJdbcDao personJdbcDao;

	public static void main(String[] args) {
		SpringApplication.run(LearnJdbcJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("\nPersons List -> {}", personJdbcDao.findAllPersons());
		logger.info("\nPersons with id = 100001 -> {}", personJdbcDao.findPersonById(100001));
		logger.info("\nPersons with location = Kaer Morhen -> {}", personJdbcDao.findPersonsByLocation("Kaer Morhen"));
		logger.info("\nDeleting person with id=100003. Total rows affected -> {}",
				personJdbcDao.deletePersonById(100003));
		logger.info("\nUpdating person with id=100001. Total rows affected -> {}",
				personJdbcDao.updatePersonById(new Person(100001, "Geralt of Rivia", "Kaer Morhen", new Date())));
		logger.info("\nInserting person with id=100003. Total rows affected -> {}",
				personJdbcDao.insertPersonById(new Person(100003, "Ciri", "Skellige", new Date())));
	}

}
