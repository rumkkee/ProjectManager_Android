package com.example.projectmanager_android.DB;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class BoardRepository {
    private BoardDAO mBoardDAO;
    private LiveData<List<Board>> mAllBoards;

    BoardRepository(Application application){
        AppDataBase db = AppDataBase.getInstance(application);
        mBoardDAO = db.BoardDAO();
        mAllBoards = mBoardDAO.getAllBoards();
    }

    public LiveData<List<Board>> getAllBoards(){
        return mAllBoards;
    }

    public LiveData<List<Board>> getBoardsByUserId(int userId){return mBoardDAO.getBoardsByUserId(userId);}

    void insert(Board board){
        AppDataBase.databaseWriteExecutor.execute(() -> {
            mBoardDAO.insert(board);
        });
    }

}
