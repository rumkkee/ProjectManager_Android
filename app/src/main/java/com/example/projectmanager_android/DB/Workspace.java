package com.example.projectmanager_android.DB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = AppDataBase.WORKSPACES_TABLE)
public class Workspace {
    @PrimaryKey(autoGenerate = true)
    private int mWorkspaceId;
    private int mUserId;
    private String mTitle;

    public Workspace(String title){
        mTitle = title;
    }

    @Override
    public String toString() {
        return
                "Workspace # " + mWorkspaceId + "\n" +
                "User # " + mUserId + "\n" +
                "Title # " + mTitle + '\n' +
                "=-=-=-=-=-=";
    }

    public int getWorkspaceId() {
        return mWorkspaceId;
    }

    public void setWorkspaceId(int workspaceId) {
        mWorkspaceId = workspaceId;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
