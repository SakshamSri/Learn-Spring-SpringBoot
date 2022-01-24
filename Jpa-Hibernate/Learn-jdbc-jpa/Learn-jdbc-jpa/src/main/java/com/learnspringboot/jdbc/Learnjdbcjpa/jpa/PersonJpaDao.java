package com.learnspringboot.jdbc.Learnjdbcjpa.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.learnspringboot.jdbc.Learnjdbcjpa.entity.Person;

@Repository
@Transactional
public class PersonJpaDao {

	@PersistenceContext
	EntityManager entityManager;

	public Person findPersonById(int id) {
		return entityManager.find(Person.class, id);
	}

	/*
	 * Insert and Update is same because it checks the id and if id is already there
	 * then it does an update otherwise it does an insert.
	 */
	public Person insertPerson(Person person) {
		return entityManager.merge(person);
	}

	public Person updatePerson(Person person) {
		return entityManager.merge(person);
	}

	public void deletePersonById(int id) {
		Person findPerson = findPersonById(id);
		if (findPerson == null)
			return;
		entityManager.remove(findPerson);
	}

	public List<Person> getAllPersons() {
		TypedQuery<Person> createNamedQuery = entityManager.createNamedQuery("get_all_persons", Person.class);
		return createNamedQuery.getResultList();
	}
}
