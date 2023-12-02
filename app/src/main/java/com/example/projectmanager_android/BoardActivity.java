package com.example.projectmanager_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.projectmanager_android.DB.AppDataBase;
import com.example.projectmanager_android.DB.Board;
import com.example.projectmanager_android.DB.BoardDAO;
import com.example.projectmanager_android.DB.Card;
import com.example.projectmanager_android.DB.CardAdapter;
import com.example.projectmanager_android.DB.CardListViewModel;
import com.example.projectmanager_android.DB.CardViewModel;
import com.example.projectmanager_android.databinding.ActivityBoardBinding;

public class BoardActivity extends AppCompatActivity implements CardDisplayer {

    ActivityBoardBinding mActivityBoardBinding;
    TextView mBoardTitleTextView;

    TextView mAddCardTextView;

    CardListViewModel mCardListViewModel;
    CardViewModel mCardViewModel;

    CardListAdderFragment mCardListAdderFragment;
    CardAdderFragment mCardAdderFragment;
    CardExpandedFragment mCardExpandedFragment;

    Board mBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityBoardBinding = ActivityBoardBinding.inflate(getLayoutInflater());
        setContentView(mActivityBoardBinding.getRoot());

        // Toolbar setup
        Toolbar toolbar = findViewById(R.id.boardActivity_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesHelper.setCurrentBoardId(SharedPreferencesHelper.INVALID_ID);
                startLandingPage();
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.deleteBoard_item){
                    deleteBoard();
                }
                return false;
            }
        });

        mBoardTitleTextView = mActivityBoardBinding.boardActivityBoardHeader;
        mAddCardTextView = mActivityBoardBinding.boardActivityAddCardClickableText;

        mBoard = AppDataBase.getInstance(this).BoardDAO().getBoardByBoardId(SharedPreferencesHelper.getCurrentBoardId());
        if(mBoard != null){
            System.out.println(mBoard.getTitle());
            mBoardTitleTextView.setText(mBoard.getTitle());
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
        RecyclerView recyclerView_cards = findViewById(R.id.boardActivity_recyclerView_cards);
        CardAdapter cardAdapter = new CardAdapter(new CardAdapter.CardDiff(), this);
        recyclerView_cards.setAdapter(cardAdapter);
        mCardViewModel = new ViewModelProvider(this).get(CardViewModel.class);
        mCardViewModel.getCardsByBoardId(SharedPreferencesHelper.getCurrentBoardId()).observe(this, cards -> {
            cardAdapter.submitList(cards);
        });

        mAddCardTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Is CardListAdder Open? : " + CardListAdderFragment.isOpen());
                if(!CardAdderFragment.isOpen()){
                    showCardAdderFragment();
                }
            }
        });

    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, BoardActivity.class);
        return intent;
    }

    private void startLandingPage(){
        Intent intent = LandingPageActivity.getIntent(getApplicationContext());
        startActivity(intent);
    }

    private void deleteBoard(){
        BoardDAO boardDAO = AppDataBase.getInstance(getApplicationContext()).BoardDAO();
        boardDAO.delete(mBoard);
        // Alerting the shared prefs that the current board value is null.
        SharedPreferencesHelper.setCurrentBoardId(SharedPreferencesHelper.INVALID_ID);
        startLandingPage();
    }

    private void showCardListAdderFragment(){
        System.out.println("CardListAdderFragment should be created and shown");
        mCardListAdderFragment = new CardListAdderFragment(mCardListViewModel);

        getSupportFragmentManager().beginTransaction()
                .add(android.R.id.content, mCardListAdderFragment)
                .addToBackStack(null)
                .commit();
    }

    public void showCardAdderFragment(){
        mCardAdderFragment = new CardAdderFragment(mCardViewModel);

        getSupportFragmentManager().beginTransaction()
                .add(android.R.id.content, mCardAdderFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void displayCardExpandedFragment(int cardId) {
        Card card = AppDataBase.getInstance(this).CardDAO().getCardByCardId(cardId);
        mCardExpandedFragment = CardExpandedFragment.newInstance(card.getTitle(), card.getDescription(), card);
        getSupportFragmentManager().beginTransaction()
                .add(android.R.id.content, mCardExpandedFragment)
                .addToBackStack(null)
                .commit();
    }
}