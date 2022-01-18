package com.jpahibernate.Learnjpahibernate.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jpahibernate.Learnjpahibernate.entity.Course;
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

		entityManager.persist(student);

		student.setPassport(passport);

	}

	public void someFunction() {
		Student student = findStudentById(201L);

		Passport passport = student.getPassport();

		student.setName("Student201-updated");

		passport.setNumber("Passport301-updated");
	}

	public void saveStudentWithCourseHardcoded() {
		Student student = new Student("Raj");
		Course course = new Course("CourseRaj");

		entityManager.persist(course);
		entityManager.persist(student);

		student.addCourse(course);
		course.addStudent(student);
	}

	public void saveStudentWithCourse(Student student, Course course) {

		entityManager.persist(course);
		entityManager.persist(student);

		student.addCourse(course);
		course.addStudent(student);
	}
}
