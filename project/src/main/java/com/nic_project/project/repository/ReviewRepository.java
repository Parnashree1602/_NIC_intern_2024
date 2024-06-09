package com.nic_project.project.repository;

import com.nic_project.project.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ReviewRepository extends MongoRepository<Review, String> {
    Optional<Review> findByDocumentId(String document_id);
}

