package com.example.projectmanager_android.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UsersDAO {
    @Insert
    void insert(Users... users);

    @Update
    void update(Users... users);

    @Delete
    void delete(Users... users);

    @Query("SELECT * FROM " + AppDataBase.USERS_TABLE)
    List<Users> getUsers();


}
