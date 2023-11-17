package com.example.projectmanager_android.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BoardDAO {
    @Insert
    void insert(Board... boards);

    @Update
    void update(Board... boards);

    @Delete
    void delete(Board... boards);

//    @Query("SELECT * FROM " + AppDataBase.BOARDS_TABLE +
//            " WHERE mWorkspaceId == :workspaceId")
//    LiveData<List<Board>> getBoardsByWorkspace(int workspaceId);
//
//    @Query("SELECT * FROM " + AppDataBase.BOARDS_TABLE +
//            " WHERE mUserId == :userId")
//    LiveData<List<Board>> getBoardsByUser(int userId);

    @Query("SELECT * FROM " + AppDataBase.BOARDS_TABLE)
    LiveData<List<Board>> getAllBoards();
}
