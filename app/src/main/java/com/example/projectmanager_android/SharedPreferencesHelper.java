package com.example.projectmanager_android;

import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public abstract class SharedPreferencesHelper {
    public static final String CURRENT_USER_ID_KEY = "currentUser_id";
    public static final String CURRENT_USER_USERNAME_KEY = "currentUser_username";
    public static final String CURRENT_USER_IS_ADMIN_KEY = "currentUser_isAdmin";
    public static final String CURRENT_BOARD_ID_KEY = "currentBoard_id";
    public static final int INVALID_ID = -1;

    private static SharedPreferences mCurrentUserPrefs;

    public static void setUserPrefs(SharedPreferences currentUserPrefs){
        mCurrentUserPrefs = currentUserPrefs;
    }

    public static boolean isUserPrefsSet(){
        return mCurrentUserPrefs != null;
    }

    public static void setCurrentUserId(int userId){
        SharedPreferences.Editor editor = mCurrentUserPrefs.edit();
        editor.putInt(CURRENT_USER_ID_KEY, userId);
        editor.apply();
    }

    public static int getCurrentUserId(){
        int currentUserId = mCurrentUserPrefs.getInt(CURRENT_USER_ID_KEY, INVALID_ID);
        System.out.println("Current user id: " + currentUserId);
        return currentUserId;
    }

    public static boolean isUserLoggedIn(){
        return getCurrentUserId() != INVALID_ID;
    }

    public static String getCurrentUsername(){
        String currentUsername = mCurrentUserPrefs.getString(CURRENT_USER_USERNAME_KEY, "");
        return currentUsername;
    }

    public static boolean isCurrentUserAdmin(){
        boolean isAdmin = mCurrentUserPrefs.getBoolean(CURRENT_USER_IS_ADMIN_KEY, false);
        return isAdmin;
    }

    public static int getCurrentBoardId(){
        int currentBoardId = mCurrentUserPrefs.getInt(CURRENT_BOARD_ID_KEY, INVALID_ID);
        return currentBoardId;
    }

    public static void setCurrentBoardId(int boardId){
        System.out.println("Set Current Board ID");
        SharedPreferences.Editor editor = mCurrentUserPrefs.edit();
        editor.putInt(CURRENT_BOARD_ID_KEY, boardId);
        editor.apply();
    }
}
