package com.learnspringboot.jdbc.Learnjdbcjpa;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.learnspringboot.jdbc.Learnjdbcjpa.entity.Person;
import com.learnspringboot.jdbc.Learnjdbcjpa.jpa.PersonJpaDao;

@SpringBootApplication
public class LearnSpringJpaApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PersonJpaDao personJpaDao;

	public static void main(String[] args) {
		SpringApplication.run(LearnSpringJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("\nPerson with id=100001 -> {}", personJpaDao.findPersonById(100001));
		logger.info("\nUpdating person with id=100001. Total rows affected -> {}",
				personJpaDao.updatePerson(new Person(100001, "Geralt of Rivia x Yen", "Kaer Morhen", new Date())));
		logger.info("\nInserting person with id=100003. Total rows affected -> {}",
				personJpaDao.insertPerson(new Person(100006, "Keira Metz", "Temeria", new Date())));
		personJpaDao.deletePersonById(100022); // does not exists
		personJpaDao.deletePersonById(100002);
		logger.info("\nPersons List -> {}", personJpaDao.getAllPersons());
	}

}
