package com.pyco.pycozza.repository;

import com.pyco.pycozza.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<User, Integer> {
    User findByEmail(String email);
    boolean existsByEmail(String email);
}
