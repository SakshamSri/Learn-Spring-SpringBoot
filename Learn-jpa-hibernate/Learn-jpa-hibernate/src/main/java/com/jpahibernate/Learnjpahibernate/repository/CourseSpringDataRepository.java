package com.jpahibernate.Learnjpahibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jpahibernate.Learnjpahibernate.entity.Course;

@RepositoryRestResource(path = "courses") // now Course is a rest resource and exposed at /courses
// Since this is a repository for Course entity so Course and the primary key of Course is Long id so Long.
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {

	// find a course by name (not inbuilt so we add some predefined ones here and
	// directly use it)
	List<Course> findByName(String name);

	// find a course by name and id
	List<Course> findByNameAndId(String name, Long id);

	// count courses with that name
	List<Course> countByName(String name);

	// find by name and order by id
	List<Course> findByNameOrderById(String name);

	// find by name and order by id DESC
	List<Course> findByNameOrderByIdDesc(String name);

	// delete by name
	List<Course> deleteByName(String name);

	// add a JPQL
	@Query("Select c from Course c where name like '%100 Steps'")
	List<Course> findCoursesJPQL();

	// add a sql native query
	@Query(value = "Select * from Course c where c.name like '%100 Steps'", nativeQuery = true)
	List<Course> findCoursesNativeQuery();

	// add a named query(defined in Course.java)
	@Query(name = "get_all_100_steps_courses")
	List<Course> findCoursesNamedQuery();

}
