package com.example.projectmanager_android.DB;

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

    @Query("SELECT * FROM " + AppDataBase.BOARDS_TABLE +
            " WHERE mWorkspaceId == :workspaceId")
    List<Board> getBoardsByWorkspace(int workspaceId);

    @Query("SELECT * FROM " + AppDataBase.BOARDS_TABLE +
            " WHERE mUserId == :userId")
    List<Board> getBoardsByUser(int userId);
}
