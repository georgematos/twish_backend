package com.octodev.twish.services;

import com.octodev.twish.models.Todo;
import com.octodev.twish.repo.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
  private ProductRepository repo;

  @Autowired
  public ProductService(ProductRepository repo) {
    this.repo = repo;
  }

  public Page<Todo> findAll(Pageable pageable) {
    return repo.findAll(pageable);
  };

}
