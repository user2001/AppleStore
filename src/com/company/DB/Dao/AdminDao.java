package com.company.DB.Dao;

import com.company.Entity.AdminEntity;

import java.sql.SQLException;
import java.util.List;

public interface AdminDao {

    List<AdminEntity> get();

    AdminEntity getAdmin(String login, String password);

    boolean findAdmin(String login);
}
