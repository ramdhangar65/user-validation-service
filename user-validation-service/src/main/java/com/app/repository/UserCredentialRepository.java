package com.app.repository;

import com.app.collection.UserCredential;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialRepository extends MongoRepository<UserCredential,Integer> {
    Optional<UserCredential> findByUsername(String username);
}
