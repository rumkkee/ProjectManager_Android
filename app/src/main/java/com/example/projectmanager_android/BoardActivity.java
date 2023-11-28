package com.example.projectmanager_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.projectmanager_android.DB.AppDataBase;
import com.example.projectmanager_android.DB.Board;
import com.example.projectmanager_android.DB.CardAdapter;
import com.example.projectmanager_android.DB.CardListAdapter;
import com.example.projectmanager_android.DB.CardListViewModel;
import com.example.projectmanager_android.DB.CardViewModel;
import com.example.projectmanager_android.databinding.ActivityBoardBinding;

public class BoardActivity extends AppCompatActivity {

    ActivityBoardBinding mActivityBoardBinding;
    ImageButton mExitButton;
    TextView mBoardTitleTextView;

    TextView mAddCardListTextView;

    CardListViewModel mCardListViewModel;
    CardViewModel mCardViewModel;

    CardListAdderFragment mCardListAdderFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityBoardBinding = ActivityBoardBinding.inflate(getLayoutInflater());
        setContentView(mActivityBoardBinding.getRoot());

        mExitButton = mActivityBoardBinding.boardActivityExitButton;
        mBoardTitleTextView = mActivityBoardBinding.boardActivityBoardHeader;
        mAddCardListTextView = mActivityBoardBinding.boardActivityAddListClickableText;

        Board currentBoard = AppDataBase.getInstance(this).BoardDAO().getBoardByBoardId(SharedPreferencesHelper.getCurrentBoardId());
        if(currentBoard != null){
            System.out.println(currentBoard.getTitle());
            mBoardTitleTextView.setText(currentBoard.getTitle());
        }

        // CardList observer setup
//        RecyclerView recyclerView_cardLists = findViewById(R.id.boardActivity_recyclerView_cardLists);
//        CardListAdapter cardListAdapter = new CardListAdapter(new CardListAdapter.CardListDiff());
//        recyclerView_cardLists.setAdapter(cardListAdapter);
//
//        mCardListViewModel = new ViewModelProvider(this).get(CardListViewModel.class);
//        mCardListViewModel.getCardListsByBoardId(SharedPreferencesHelper.getCurrentBoardId()).observe(this, cardLists -> {
//            cardListAdapter.submitList(cardLists);
//        });

        // Card observer setup
//        RecyclerView recyclerView_cards = recyclerView_cardLists.findViewById(R.id.);
//        CardAdapter cardAdapter = new CardAdapter(new CardAdapter.CardDiff());
//        recyclerView_cards.setAdapter(cardAdapter);
//        mCardViewModel = new ViewModelProvider(this).get(CardViewModel.class);
//        mCardViewModel.getAllCards().observe(this, cards -> {
//            cardAdapter.submitList(cards);
//        });

        mExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Signals that the user is no longer in a BoardActivity.
                SharedPreferencesHelper.setCurrentBoardId(-1);

                Intent intent = LandingPageActivity.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });

        mAddCardListTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Is CardListAdder Open? : " + CardListAdderFragment.isOpen());
                if(!CardListAdderFragment.isOpen()){
                    showCardListAdderFragment();
                }
            }
        });

    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, BoardActivity.class);
        return intent;
    }

    private void showCardListAdderFragment(){
        System.out.println("CardListAdderFragment should be created and shown");
        mCardListAdderFragment = new CardListAdderFragment(mCardListViewModel);

        getSupportFragmentManager().beginTransaction()
                .add(android.R.id.content, mCardListAdderFragment)
                .addToBackStack(null)
                .commit();
    }

}