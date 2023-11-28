package com.example.projectmanager_android;

import com.example.projectmanager_android.DB.Card;
import com.example.projectmanager_android.DB.CardViewModel;

public class CardAdderFragment extends ItemAdderFragment{
    CardViewModel mCardViewModel;

    public CardAdderFragment(CardViewModel cardViewModel) {
        mCardViewModel = cardViewModel;
    }

    protected void addItem(String itemName){
        Card card = new Card();
        card.setTitle(itemName);
        card.setUserId(SharedPreferencesHelper.getCurrentUserId());
        card.setBoardId(SharedPreferencesHelper.getCurrentBoardId());
        mCardViewModel.insert(card);
        removeFragment();
    }

}
