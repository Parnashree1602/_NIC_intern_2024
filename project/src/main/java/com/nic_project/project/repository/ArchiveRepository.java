package com.nic_project.project.repository;

import com.nic_project.project.model.ArchiveDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface ArchiveRepository extends MongoRepository<ArchiveDocument, Long> {
    @Query("{'applicationTransactionId' :  ?0 }")
    Optional<ArchiveDocument> findByApplicationTransactionId(Long applicationTransactionId);
}
