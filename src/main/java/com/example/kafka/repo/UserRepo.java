package com.example.kafka.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.kafka.domain.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long>{

}
