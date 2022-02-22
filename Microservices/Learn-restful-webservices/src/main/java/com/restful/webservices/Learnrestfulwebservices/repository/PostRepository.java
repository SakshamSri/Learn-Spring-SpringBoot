package com.restful.webservices.Learnrestfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restful.webservices.Learnrestfulwebservices.bean.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
