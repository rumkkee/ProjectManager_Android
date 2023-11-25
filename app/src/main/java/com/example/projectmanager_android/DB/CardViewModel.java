package com.example.projectmanager_android.DB;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CardViewModel extends AndroidViewModel {
    private CardRepository mRepository;
    private final LiveData<List<Card>> mAllCards;

    public CardViewModel(@NonNull Application application) {
        super(application);
        mRepository = new CardRepository(application);
        mAllCards = mRepository.getAllCards();
    }

    public LiveData<List<Card>> getAllCards(){return mAllCards;}
    public LiveData<List<Card>> getCardsByUserId(int userId){return mRepository.getCardsByUserId(userId);}
    public LiveData<List<Card>> getCardsByBoardId(int boardId){return mRepository.getCardsByBoardId(boardId);}
    public LiveData<List<Card>> getCardsByCardListId(int cardListId){return mRepository.getCardsByCardListId(cardListId);}

    public void insert(Card card){mRepository.insert(card);}
}
