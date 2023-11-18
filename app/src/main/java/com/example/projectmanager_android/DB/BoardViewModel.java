package com.example.projectmanager_android.DB;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class BoardViewModel extends AndroidViewModel {
    private BoardRepository mRepository;
    private final LiveData<List<Board>> mAllBoards;

    public BoardViewModel(@NonNull Application application) {
        super(application);
        mRepository = new BoardRepository(application);
        mAllBoards = mRepository.getAllBoards();
    }

    public LiveData<List<Board>> getAllBoards(){
        return mAllBoards;
    }
    public LiveData<List<Board>> getBoardsByUserId(int userId){return mRepository.getBoardsByUserId(userId);}
    public void insert(Board board){
        mRepository.insert(board);
    }

}
