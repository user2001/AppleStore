package com.company.DB.Dao;

import com.company.Entity.UserEntity;

import java.util.List;

public interface UserDao {

    void singUpUser(UserEntity user);

    UserEntity getUser(String login, String password);

    boolean findUser(String login);

    List<UserEntity> get();

    void update(String status,int id);
}
