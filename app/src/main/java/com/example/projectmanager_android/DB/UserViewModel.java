package com.example.projectmanager_android.DB;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserRepository mRepository;
    private final LiveData<List<Users>> mAllUsers;

    public UserViewModel(Application application){
        super(application);
        mRepository = new UserRepository(application);
        mAllUsers = mRepository.getAllUsers();
    }

    public LiveData<List<Users>> getAllUsers(){
        return mAllUsers;
    }

    public void insert(Users user){
        mRepository.insert(user);
    }
}
