package com.example.projectmanager_android.DB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName = AppDataBase.BOARDS_TABLE)
public class Card {
    @PrimaryKey(autoGenerate = true)
    private int mCardId;
    private int mCardListId;
    private int mUserId;

    private String mTitle;
    private String mDescription;
    private Date mStartDate;
    private Date mDueDate;
    private int mPositionInList;

    @Override
    public String toString() {
        return
                "Card # " + mCardId + "\n" +
                "Title: " + mTitle + "\n" +
                "CardList # " + mCardListId + "\n" +
                "Position In List: " + mPositionInList + "\n" +
                "User # " + mUserId + "\n" +
                "=-=-=-=-=-=";
    }

    public int getCardId() {
        return mCardId;
    }

    public void setCardId(int cardId) {
        mCardId = cardId;
    }

    public int getCardListId() {
        return mCardListId;
    }

    public void setCardListId(int cardListId) {
        mCardListId = cardListId;
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

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Date getStartDate() {
        return mStartDate;
    }

    public void setStartDate(Date startDate) {
        mStartDate = startDate;
    }

    public Date getDueDate() {
        return mDueDate;
    }

    public void setDueDate(Date dueDate) {
        mDueDate = dueDate;
    }

    public int getPositionInList() {
        return mPositionInList;
    }

    public void setPositionInList(int positionInList) {
        mPositionInList = positionInList;
    }
}
