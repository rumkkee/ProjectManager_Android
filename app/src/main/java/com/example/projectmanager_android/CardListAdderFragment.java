package com.example.projectmanager_android;

import com.example.projectmanager_android.DB.CardList;
import com.example.projectmanager_android.DB.CardListViewModel;

public class CardListAdderFragment extends ItemAdderFragment {

    private CardListViewModel mCardListViewModel;

    public CardListAdderFragment(){
        super();
    }

    public CardListAdderFragment(CardListViewModel cardListViewModel){
        this();
        setViewModel(cardListViewModel);
    }

    @Override
    protected void addItem(String itemName) {
        System.out.println("Adding CardList Item!");
        System.out.println("Current User Id: " + SharedPreferencesHelper.getCurrentUserId());
        System.out.println("Current Board Id:" + SharedPreferencesHelper.getCurrentBoardId());
        CardList cardList = new CardList(itemName);
        cardList.setUserId(SharedPreferencesHelper.getCurrentUserId());
        cardList.setBoardId(SharedPreferencesHelper.getCurrentBoardId());
        mCardListViewModel.insert(cardList);
        removeFragment();
    }

    public void setViewModel(CardListViewModel cardListViewModel){
        mCardListViewModel = cardListViewModel;
    }

}
