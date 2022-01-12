package com.learnspringboot.jdbc.Learnjdbcjpa.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.learnspringboot.jdbc.Learnjdbcjpa.entity.Person;

@Repository
public class PersonJdbcDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	class PersonRowMapper implements RowMapper<Person> {

		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person person = new Person();
			person.setId(rs.getInt("id"));
			person.setName(rs.getString("name"));
			person.setLocation(rs.getString("location"));
			person.setBirthDate(rs.getTimestamp("birth_date"));
			return person;
		}

	}

	// method for -> select * from person
	public List<Person> findAllPersons() {
		return jdbcTemplate.query("select * from person", new PersonRowMapper());
	}

	// method for -> select * from person where id=?
	public Person findPersonById(int id) {
		return jdbcTemplate.queryForObject("select * from person where id=?",
				new BeanPropertyRowMapper<Person>(Person.class), new Object[] { id });
	}

	// method for -> select * from person where location=?
	public List<Person> findPersonsByLocation(String location) {
		return jdbcTemplate.query("select * from person where location=?",
				new BeanPropertyRowMapper<Person>(Person.class), new Object[] { location });
	}

	// method for -> delete from person where id=?
	public int deletePersonById(int id) {
		return jdbcTemplate.update("delete from person where id=?", new Object[] { id });
	}

	// method for -> update person set name=?, location=?, birth_date=? where id=?
	public int updatePersonById(Person person) {
		return jdbcTemplate.update("update person set name=?, location=?, birth_date=? where id=?",
				new Object[] { person.getName(), person.getLocation(), new Timestamp(person.getBirthDate().getTime()),
						person.getId() });
	}

	// method for -> insert into person(id, name, location, birth_date) values
	// (?,?,?,?)
	public int insertPersonById(Person person) {
		return jdbcTemplate.update("insert into person (id, name, location, birth_date) values (?, ?, ?, ?)",
				new Object[] { person.getId(), person.getName(), person.getLocation(),
						new Timestamp(person.getBirthDate().getTime()) });
	}
}
