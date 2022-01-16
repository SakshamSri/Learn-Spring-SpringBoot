package com.jpahibernate.Learnjpahibernate.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jpahibernate.Learnjpahibernate.entity.Passport;
import com.jpahibernate.Learnjpahibernate.entity.Student;

@Repository
@Transactional
public class StudentRepository {

	@Autowired
	EntityManager entityManager;

	public Student findStudentById(Long id) {
		return entityManager.find(Student.class, id);
	}

	public Student saveStudent(Student student) { // insert + update
		if (student.getId() == null)
			entityManager.persist(student); // insert
		else
			entityManager.merge(student); // update
		return student;
	}

	public void deleteStudentById(Long id) {
		Student findStudent = findStudentById(id);
		if (findStudent != null)
			entityManager.remove(findStudent);
	}

	public void saveStudentWithPassport() {
		Student student = new Student("Raj");
		Passport passport = new Passport("PassportRaj");

		entityManager.persist(passport);

		student.setPassport(passport);

		entityManager.persist(student);
	}
}
