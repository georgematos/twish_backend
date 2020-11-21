package com.octodev.twish.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "todo")
public class Todo implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private String id;

  private String description;
  private LocalDateTime startTime;
  private LocalDateTime deadLine;
  private Boolean status;

  public Todo() {
  }

  public Todo(String id, String description, LocalDateTime startTime, LocalDateTime deadLine) {
    this.id = id;
    this.description = description;
    this.startTime = startTime;
    this.deadLine = deadLine;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDateTime getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
  }

  public LocalDateTime getDeadLine() {
    return deadLine;
  }

  public void setDeadLine(LocalDateTime deadLine) {
    this.deadLine = deadLine;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Todo other = (Todo) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}
