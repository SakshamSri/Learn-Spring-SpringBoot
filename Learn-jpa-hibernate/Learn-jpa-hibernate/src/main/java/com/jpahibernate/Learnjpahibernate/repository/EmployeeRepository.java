package com.jpahibernate.Learnjpahibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jpahibernate.Learnjpahibernate.entity.Employee;

@Repository
@Transactional
public class EmployeeRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager entityManager;

	public Employee findEmployee(Long id) {
		return entityManager.find(Employee.class, id);
	}

	public void saveEmployee(Employee employee) {
		entityManager.persist(employee);
	}

	// To be used only when using Inheritance since in MappedSuperClass Employee is
	// not an entity
	public List<Employee> getEmployees() {
		Query query = entityManager.createQuery("select e from Employee e", Employee.class);
		return query.getResultList();
	}

	public List<Employee> getFullTimeEmployees() {
		Query query = entityManager.createQuery("select e from FullTimeEmployee e", Employee.class);
		return query.getResultList();
	}

	public List<Employee> getPartTimeEmployees() {
		Query query = entityManager.createQuery("select e from PartTimeEmployee e", Employee.class);
		return query.getResultList();
	}

}
