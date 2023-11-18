package com.example.projectmanager_android.DB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = AppDataBase.BOARDS_TABLE)
public class Board {
    @PrimaryKey(autoGenerate = true)
    private int mBoardId;

    private int mWorkspaceId;
    private int mUserId;
    private String mTitle;
    private boolean isStarred;

    public Board(String title){
        mTitle = title;
        isStarred = false;
    }

    @Override
    public String toString() {
        return
                "Board # " + mBoardId + "\n" +
                "Workspace # " + mWorkspaceId + "\n" +
                "User # " + mUserId + "\n" +
                "Title: " + mTitle + '\n' +
                "Starred: " + isStarred + "\n" +
                "=-=-=-=-=-=";
    }

    public int getBoardId() {
        return mBoardId;
    }

    public void setBoardId(int boardId) {
        mBoardId = boardId;
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

    public boolean isStarred() {
        return isStarred;
    }

    public void setStarred(boolean starred) {
        isStarred = starred;
    }
}
