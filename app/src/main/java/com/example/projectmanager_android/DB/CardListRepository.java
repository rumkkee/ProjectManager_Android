package com.example.projectmanager_android.DB;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CardListRepository {
    private CardListDAO mCardListDAO;
    private LiveData<List<CardList>> mAllCardLists;

    CardListRepository(Application application){
        AppDataBase db = AppDataBase.getInstance(application);
        mCardListDAO = db.CardListDAO();
        mAllCardLists = mCardListDAO.getAllLists();
    }

    public LiveData<List<CardList>> getAllCardLists(){return mAllCardLists;}

    public LiveData<List<CardList>> getCardListsByUserId(int userId){return mCardListDAO.getListsbyUserId(userId);}

    public LiveData<List<CardList>> getCardListsByBoardId(int boardId){return mCardListDAO.getListsbyBoardId(boardId);}

    void insert(CardList cardList){
        AppDataBase.databaseWriteExecutor.execute(() -> {
            mCardListDAO.insert(cardList);
        });
    }

}
