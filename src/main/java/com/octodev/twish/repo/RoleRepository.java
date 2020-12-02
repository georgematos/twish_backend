package com.octodev.twish.repo;

import java.util.Optional;

import com.octodev.twish.models.ERole;
import com.octodev.twish.models.Role;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String>{
  Optional<Role> findByName(ERole name);
}
