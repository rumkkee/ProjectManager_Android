package com.example.projectmanager_android.DB;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CardRepository {
    private CardDAO mCardDAO;
    private LiveData<List<Card>> mAllCards;

    CardRepository(Application application){
        AppDataBase db = AppDataBase.getInstance(application);
        mCardDAO = db.CardDAO();
        mAllCards = mCardDAO.getAllCards();
    }

    public LiveData<List<Card>> getAllCards(){return mAllCards;}

    public LiveData<List<Card>> getCardsByUserId(int userId){return mCardDAO.getCardsByUserId(userId);}

    public LiveData<List<Card>> getCardsByCardListId(int cardListId){return mCardDAO.getCardsByCardListId(cardListId);}

    void insert(Card card){
        AppDataBase.databaseWriteExecutor.execute(() -> {
            mCardDAO.insert(card);
        });
    }

}
