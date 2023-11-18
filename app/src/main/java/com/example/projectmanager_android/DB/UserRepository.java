package com.example.projectmanager_android.DB;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {
    private UserDAO mUserDAO;
    private LiveData<List<User>> mAllUsers;

    UserRepository(Application application){
        AppDataBase db = AppDataBase.getInstance(application);
        mUserDAO = db.UserDAO();
        //mAllUsers = mUserDAO.getUsers();
    }

    public LiveData<List<User>> getAllUsers(){
        return mAllUsers;
    }

    void insert(User user){
        AppDataBase.databaseWriteExecutor.execute(() -> {
            mUserDAO.insert(user);
        });
    }
}
