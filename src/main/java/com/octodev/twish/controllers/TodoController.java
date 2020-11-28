package com.octodev.twish.controllers;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.octodev.twish.models.Todo;
import com.octodev.twish.services.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/todo")
public class TodoController {

  private TodoService service;

  @Autowired
  public TodoController(TodoService service) {
    this.service = service;
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Todo> findById(@PathVariable String id) {
    Todo todo = service.findById(id);

    return ResponseEntity.ok().body(todo);
  }

  @GetMapping
  public ResponseEntity<Map<String, Object>> findAll(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "3") int size) {

    Pageable paging = PageRequest.of(page, size);
    Page<Todo> pageTodos = service.findAll(paging);
    List<Todo> todos = pageTodos.getContent();

    Map<String, Object> response = new HashMap<>();
    response.put("todos", todos);
    response.put("currentPage", pageTodos.getNumber());
    response.put("totalItems", pageTodos.getTotalElements());
    response.put("totalPages", pageTodos.getTotalPages());

    return ResponseEntity.ok().body(response);
  }

  @PostMapping
  public ResponseEntity<Todo> save(@RequestBody Todo todo) {
    Todo todoSaved = service.save(todo);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(todoSaved.getId()).toUri();
    return ResponseEntity.created(uri).body(todoSaved);
  }

  @PutMapping
  public ResponseEntity<Todo> update(@RequestBody Todo todo) {
    Todo updatedTodo = service.update(todo);

    return ResponseEntity.ok().body(updatedTodo);
  }

  @DeleteMapping(value = "/{id}")
  public void delete(@PathVariable String id) {
    service.delete(id);
  }

}
