package com.example.demo.redis;


import com.example.demo.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;


public interface UserEntityRepository extends CrudRepository<UserEntity,String> {
    UserEntity findByUserNameAndPassWord(String username,String password);

}
