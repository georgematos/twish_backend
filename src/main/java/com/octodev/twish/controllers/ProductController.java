package com.octodev.twish.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.octodev.twish.models.Todo;
import com.octodev.twish.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

  private ProductService service;

  @Autowired
  public ProductController(ProductService service) {
    this.service = service;
  }

  @GetMapping("/todo")
  public ResponseEntity<Map<String, Object>> findAll(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "3") int size) {

    Pageable paging = PageRequest.of(page, size);
    Page<Todo> pageProducts = service.findAll(paging);
    List<Todo> products = pageProducts.getContent();

    Map<String, Object> response = new HashMap<>();
    response.put("products", products);
    response.put("currentPage", pageProducts.getNumber());
    response.put("totalItems", pageProducts.getTotalElements());
    response.put("totalPages", pageProducts.getTotalPages());

    return ResponseEntity.ok().body(response);
  }

}
