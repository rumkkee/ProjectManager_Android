package com.example.projectmanager_android.DB;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {
    private UsersDAO mUsersDAO;
    private LiveData<List<Users>> mAllUsers;

    UserRepository(Application application){
        AppDataBase db = AppDataBase.getInstance(application);
        mUsersDAO = db.UserDAO();
        mAllUsers = mUsersDAO.getUsers();
    }

    public LiveData<List<Users>> getAllUsers(){
        return mAllUsers;
    }

    void insert(Users user){
        AppDataBase.databaseWriteExecutor.execute(() -> {
            mUsersDAO.insert(user);
        });
    }
}
