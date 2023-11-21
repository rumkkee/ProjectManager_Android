package com.example.projectmanager_android.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
/**
 * @author Arturo Cesareo-Zacarias
 * @since 11/17/2023
 * The start of the UserDAO interface
 */
@Dao
public interface UserDAO {
    @Insert
    void insert(User... users);

    @Update
    void update(User... users);

    @Delete
    void delete(User... users);

    @Query("SELECT * FROM " + AppDataBase.USERS_TABLE)
    List<User> getAllUsers();

    @Query("SELECT * FROM " + AppDataBase.USERS_TABLE +
            " WHERE mUserId == :userId")
    User getUserByID(int userId);

    @Query("SELECT * FROM " + AppDataBase.USERS_TABLE +
            " WHERE mUsername == :username")
    List<User> getUserByUsername(String username);


}
