package com.example.mongodb_springboot.Repositories;


import com.example.mongodb_springboot.Models.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AdminRepository extends MongoRepository<Admin, String> {
    Optional<Admin> findByUsername(String username);

}
