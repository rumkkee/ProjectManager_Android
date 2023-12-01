package com.example.projectmanager_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.projectmanager_android.DB.Board;
import com.example.projectmanager_android.DB.BoardListAdapter;
import com.example.projectmanager_android.DB.BoardViewModel;
import com.example.projectmanager_android.databinding.ActivityLandingPageBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LandingPageActivity extends AppCompatActivity {

    ActivityLandingPageBinding mLandingPageBinding;

    FloatingActionButton mAddBoardButton;

    TextView mUserGreeting;

    Button mLogOutButton;

    Button mAdminButton;

    BoardViewModel mBoardViewModel;

    BoardSetupFragment mBoardSetupFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLandingPageBinding = ActivityLandingPageBinding.inflate(getLayoutInflater());
        setContentView(mLandingPageBinding.getRoot());

        mAddBoardButton = mLandingPageBinding.addBoardFab;
        mLogOutButton = mLandingPageBinding.LogOutButton;
        mUserGreeting = mLandingPageBinding.userGreeting;

        setUserGreeting();
        adminCheck();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        BoardListAdapter adapter = new BoardListAdapter(new BoardListAdapter.BoardDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mBoardViewModel = new ViewModelProvider(this).get(BoardViewModel.class);
        mBoardViewModel.getBoardsByUserId(SharedPreferencesHelper.getCurrentUserId()).observe(this, boards -> {
            adapter.submitList(boards);
        });

        mAddBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBoardSetupFragment();
            }
        });

        mLogOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Signals to the SharedPreferences that no user is logged in
                SharedPreferencesHelper.setCurrentUserId(SharedPreferencesHelper.INVALID_ID);
                // Starts Main Activity
                Intent intent = MainActivity.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });

//        adapter.setOnItemClickListener(new BoardListAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(Board board) {
//                System.out.println("Board clicked successfully!");
//                System.out.println(board);
////                Intent intent = BoardActivity.getIntent(getApplicationContext());
////                startActivity(intent);
//            }
//        });
    }

    private void showBoardSetupFragment(){
        if(!BoardSetupFragment.isOpen()){
            mBoardSetupFragment = new BoardSetupFragment();
            mBoardSetupFragment.setViewModel(mBoardViewModel);
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, mBoardSetupFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    private void adminCheck() {
        boolean isAdmin = SharedPreferencesHelper.isCurrentUserAdmin();
        if(isAdmin){
            mAdminButton = mLandingPageBinding.adminButton;
            mAdminButton.setVisibility(View.VISIBLE);
        }
    }

    private void setUserGreeting(){
        String currentUsername = SharedPreferencesHelper.getCurrentUsername();
        mUserGreeting.setText(getString(R.string.userGreeting) + currentUsername);
    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, LandingPageActivity.class);
        return intent;
    }
}