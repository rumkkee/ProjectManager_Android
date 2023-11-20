package com.example.projectmanager_android.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface CardListDAO {
    @Insert
    void insert(CardList... cardLists);

    @Update
    void update(CardList... cardLists);

    @Delete
    void delete(CardList... cardLists);

    @Query("SELECT * FROM " + AppDataBase.LISTS_TABLE)
    LiveData<List<CardList>> getAllLists();

    @Query("SELECT * FROM " + AppDataBase.LISTS_TABLE +
            " WHERE mBoardId == :boardId")
    LiveData<List<CardList>> getListsbyBoardId(int boardId);

    @Query("SELECT * FROM " + AppDataBase.LISTS_TABLE +
            " WHERE mUserId == :userid")
    LiveData<List<CardList>> getListsbyUserId(int userid);


}
