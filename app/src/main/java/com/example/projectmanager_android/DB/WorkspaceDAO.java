package com.example.projectmanager_android.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WorkspaceDAO {
    @Insert
    void insert(Workspace... workspaces);

    @Update
    void update(Workspace... workspaces);

    @Delete
    void delete(Workspace... workspaces);

    @Query("SELECT * FROM " + AppDataBase.WORKSPACES_TABLE +
            " WHERE mUserId == :userId")
    List<Board> getWorkspaces(int userId);
}
