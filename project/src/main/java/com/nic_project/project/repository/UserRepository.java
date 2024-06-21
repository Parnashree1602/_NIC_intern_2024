package com.nic_project.project.repository;

import com.nic_project.project.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    @Query("{'client_id': ?0}")
    Optional<User> findByClientId(String client_id);
}
