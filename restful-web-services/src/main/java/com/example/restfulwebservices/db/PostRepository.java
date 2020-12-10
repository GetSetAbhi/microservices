package com.example.restfulwebservices.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restfulwebservices.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
