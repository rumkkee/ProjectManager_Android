package com.example.projectmanager_android;

import android.content.SharedPreferences;

public class SharedPreferencesHelper {
    public static final String CURRENT_USER_ID_KEY = "currentUser_id";
    public static final String CURRENT_USER_USERNAME_KEY = "currentUser_username";
    public static final String CURRENT_USER_IS_ADMIN_KEY = "currentUser_isAdmin";
    private SharedPreferences mCurrentUserPrefs;

    public SharedPreferencesHelper(SharedPreferences currentUserPrefs) {
        mCurrentUserPrefs = currentUserPrefs;
    }

    public int getCurrentUserId(){
        int currentUserId = mCurrentUserPrefs.getInt(CURRENT_USER_ID_KEY, -1);
        System.out.println("Current user id: " + currentUserId);
        return currentUserId;
    }

    public String getCurrentUsername(){
        String currentUsername = mCurrentUserPrefs.getString(CURRENT_USER_USERNAME_KEY, "");
        return currentUsername;
    }

    public boolean isCurrentUserAdmin(){
        boolean isAdmin = mCurrentUserPrefs.getBoolean(CURRENT_USER_IS_ADMIN_KEY, false);
        return isAdmin;
    }




}
