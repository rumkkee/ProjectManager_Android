package com.example.projectmanager_android.DB;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CardListViewModel extends AndroidViewModel {
    private CardListRepository mRepository;
    private final LiveData<List<CardList>> mAllCardLists;

    public CardListViewModel(@NonNull Application application) {
        super(application);
        mRepository = new CardListRepository(application);
        mAllCardLists = mRepository.getAllCardLists();
    }

    public LiveData<List<CardList>> getAllCardLists(){return mAllCardLists;}
    public LiveData<List<CardList>> getCardListsByUserId(int userId){return mRepository.getCardListsByUserId(userId);}
    public LiveData<List<CardList>> getCardListsByBoardId(int boardId){return mRepository.getCardListsByBoardId(boardId);}

    public void insert(CardList cardList){mRepository.insert(cardList);}

}
