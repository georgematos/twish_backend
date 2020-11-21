package com.octodev.twish.services;

import com.octodev.twish.models.Todo;
import com.octodev.twish.repo.TodoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
  private TodoRepository repo;

  @Autowired
  public TodoService(TodoRepository repo) {
    this.repo = repo;
  }

  public Page<Todo> findAll(Pageable pageable) {
    return repo.findAll(pageable);
  };

  public Todo save(Todo todo) {
    return repo.save(todo);
  }

  public Todo findById(String id) {
    return repo.findById(id).get();
  }

  public void delete(String id) {
    try {
      repo.deleteById(id);
    } catch (Exception e) {
      throw e;
    }
  }

  public Todo update(Todo todo) {
    Todo document = repo.findById(todo.getId()).get();

    updateTodo(todo, document);

    return repo.save(document);
  }

  private void updateTodo(Todo todo, Todo document) {
    document.setDescription(todo.getDescription());
    document.setStartTime(todo.getStartTime());
    document.setDeadLine(todo.getDeadLine());
    document.setStatus(todo.getStatus());
  }

}
