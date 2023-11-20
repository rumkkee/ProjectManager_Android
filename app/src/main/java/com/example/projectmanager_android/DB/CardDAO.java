package com.example.projectmanager_android.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CardDAO {
    @Insert
    void insert(Card... cards);

    @Update
    void update(Card... cards);

    @Delete
    void delete(Card... cards);

    @Query("SELECT * FROM " + AppDataBase.CARDS_TABLE)
    LiveData<List<Card>> getAllCards();

    @Query("SELECT * FROM " + AppDataBase.CARDS_TABLE +
            " WHERE mUserId == :userId")
    LiveData<List<Card>> getCardsByUserId(int userId);

    @Query("SELECT * FROM " + AppDataBase.CARDS_TABLE +
            " WHERE mCardListId == :cardListId")
    LiveData<List<Card>> getCardsByCardListId(int cardListId);
}
