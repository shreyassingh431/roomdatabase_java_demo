package com.example.demoapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAll();


    @Insert
    void insert(User user);

    @Query("SELECT * FROM USER WHERE email_id = :userName")
    int isDataExist(String userName);
}
