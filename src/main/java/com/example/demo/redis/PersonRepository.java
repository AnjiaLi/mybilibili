package com.example.demo.redis;

import com.example.demo.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<UserEntity,String> {
    List<UserEntity> findByLastname(String lastname);
    Page<UserEntity> findPersonByLastname(String lastname, Pageable page);
    List<UserEntity> findByFirstnameAndLastname(String firstname,String lastname);
    List<UserEntity> findByAddress_City(String city);
    List<UserEntity> findByFamilyList_Username(String username);
}
