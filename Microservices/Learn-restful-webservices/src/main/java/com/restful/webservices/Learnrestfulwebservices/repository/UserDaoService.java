package com.restful.webservices.Learnrestfulwebservices.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.restful.webservices.Learnrestfulwebservices.bean.User;

@Repository
@Transactional
public class UserDaoService {

	@Autowired
	EntityManager entityManager;

	public List<User> findAll() {
		return entityManager.createQuery("Select u from User u", User.class).getResultList();
	}

	public User save(User user) {
		if (user.getId() == null)
			entityManager.persist(user);
		else
			entityManager.merge(user);
		return user;
	}

	public User findUser(Long id) {
		return entityManager.find(User.class, id);
	}

	public void deleteUser(User user) {
		entityManager.remove(user);
	}
}
