package com.example.projectmanager_android.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Users.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public static final String DATABASE_NAME = "ProjectManager_android.db";
    public static final String USERS_TABLE = "users_table";
    public static final String WORKSPACES_TABLE = "workspaces_table";
    public static final String BOARDS_TABLE = "boards_table";
    public static final String LISTS_TABLE = "lists_table";
    public static final String CARDS_TABLE = "cards_table";

    private static volatile AppDataBase instance;
    private static final Object LOCK = new Object();

    public abstract UsersDAO UserDAO();

    public static AppDataBase getInstance(Context context){
        if(instance == null){
            synchronized (LOCK){
                if(instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class,
                            DATABASE_NAME).build();
//                    Users testUser1 = createUser("testuser1", "testuser1", false);
//                    Users testAdmin2 = createUser("admin2", "admin2", true);
//                    instance.UserDAO().insert(testUser1, testAdmin2);
                }
            }
        }
        return instance;
    }

    public static Users createUser(String name, String password, boolean isAdmin){
        Users userCredentials = new Users(name, password);
        userCredentials.setIsAdmin(isAdmin);
        return userCredentials;
    }
}
