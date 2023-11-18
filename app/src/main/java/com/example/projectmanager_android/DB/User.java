package com.example.projectmanager_android.DB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = AppDataBase.USERS_TABLE)
public class User {
    @PrimaryKey(autoGenerate = true)
    private int mUserId;
    private String mUsername;
    private String mPassword;
    private boolean mIsAdmin;

    public User(String username, String password) {
        mUsername = username;
        mPassword = password;
    }

    @Override
    public String toString() {
        return "User # " + mUserId + "\n" +
                "Username: " + mUsername + "\n" +
                "Password: " + mPassword + "\n" +
                "IsAdmin: " + mIsAdmin + "\n" +
                "=-=-=-=-=-=-";
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public boolean getIsAdmin() {
        return mIsAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        mIsAdmin = isAdmin;
    }
}
