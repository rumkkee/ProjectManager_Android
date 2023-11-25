package com.example.projectmanager_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.projectmanager_android.DB.AppDataBase;
import com.example.projectmanager_android.DB.Board;
import com.example.projectmanager_android.DB.CardListAdapter;
import com.example.projectmanager_android.DB.CardListViewModel;
import com.example.projectmanager_android.databinding.ActivityBoardBinding;

public class BoardActivity extends AppCompatActivity {

    ActivityBoardBinding mActivityBoardBinding;
    ImageButton mExitButton;
    TextView mBoardTitleTextView;

    TextView mAddCardTextView;

    CardListViewModel mCardListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityBoardBinding = ActivityBoardBinding.inflate(getLayoutInflater());
        setContentView(mActivityBoardBinding.getRoot());

        mExitButton = mActivityBoardBinding.boardActivityExitButton;
        mBoardTitleTextView = mActivityBoardBinding.boardActivityBoardHeader;
        mAddCardTextView = mActivityBoardBinding.boardActivityAddListClickableText;

        Board currentBoard = AppDataBase.getInstance(this).BoardDAO().getBoardByBoardId(SharedPreferencesHelper.getCurrentBoardId());
        if(currentBoard != null){
            System.out.println(currentBoard.getTitle());
            mBoardTitleTextView.setText(currentBoard.getTitle());
        }

        RecyclerView recyclerView_cardLists = findViewById(R.id.boardActivity_recyclerView_cardLists);
        CardListAdapter cardListAdapter = new CardListAdapter(new CardListAdapter.CardListDiff());
        recyclerView_cardLists.setAdapter(cardListAdapter);

        mCardListViewModel = new ViewModelProvider(this).get(CardListViewModel.class);
        mCardListViewModel.getCardListsByBoardId(SharedPreferencesHelper.getCurrentBoardId()).observe(this, cardLists -> {
            cardListAdapter.submitList(cardLists);
        });

        mExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Signals that the user is no longer in a BoardActivity.
                SharedPreferencesHelper.setCurrentBoardId(-1);

                Intent intent = LandingPageActivity.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });

        mAddCardTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Create a Fragment for adding a CardList
                //  Have this click event open that fragment.
            }
        });

    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, BoardActivity.class);
        return intent;
    }
}