package com.octodev.twish.repo;

import com.octodev.twish.models.Todo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Todo, String> {

  Page<Todo> findAll(Pageable pageable);

}
