package com.example.projectmanager_android;

import android.content.SharedPreferences;

public abstract class SharedPreferencesHelper {
    public static final String CURRENT_USER_ID_KEY = "currentUser_id";
    public static final String CURRENT_USER_USERNAME_KEY = "currentUser_username";
    public static final String CURRENT_USER_IS_ADMIN_KEY = "currentUser_isAdmin";
    public static final String CURRENT_BOARD_ID_KEY = "currentBoard_id";

    private static SharedPreferences mCurrentUserPrefs;

    public static void setUserPrefs(SharedPreferences currentUserPrefs){
        mCurrentUserPrefs = currentUserPrefs;
    }

    public static int getCurrentUserId(){
        int currentUserId = mCurrentUserPrefs.getInt(CURRENT_USER_ID_KEY, -1);
        System.out.println("Current user id: " + currentUserId);
        return currentUserId;
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
        int currentBoardId = mCurrentUserPrefs.getInt(CURRENT_BOARD_ID_KEY, -1);
        return currentBoardId;
    }

    public static void setCurrentBoardId(int boardId){
        System.out.println("Set Current Board ID");
        SharedPreferences.Editor editor = mCurrentUserPrefs.edit();
        editor.putInt(CURRENT_BOARD_ID_KEY, boardId);
        editor.apply();
    }
}
