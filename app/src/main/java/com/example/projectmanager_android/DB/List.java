package com.example.projectmanager_android.DB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = AppDataBase.LISTS_TABLE)
public class List {
    @PrimaryKey(autoGenerate = true)
    private int mListId;
    private int mBoardId;
    private int mUserId;

    private String mTitle;
    private int positionOnBoard;

    public List(String title){
        mTitle = title;
    }

    @Override
    public String toString() {
        return
                "List #" + mListId + "\n" +
                "Board Id #" + mBoardId + "\n" +
                "User Id #" + mUserId + "\n" +
                "Title: " + mTitle + "\n" +
                "Position on Board: " + positionOnBoard + "\n" +
                "=-=-=-=-=-=";
    }

    public int getListId() {
        return mListId;
    }

    public void setListId(int listId) {
        mListId = listId;
    }

    public int getBoardId() {
        return mBoardId;
    }

    public void setBoardId(int boardId) {
        mBoardId = boardId;
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

    public int getPositionOnBoard() {
        return positionOnBoard;
    }

    public void setPositionOnBoard(int positionOnBoard) {
        this.positionOnBoard = positionOnBoard;
    }
}
