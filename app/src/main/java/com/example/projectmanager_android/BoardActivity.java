package com.example.projectmanager_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.projectmanager_android.DB.AppDataBase;
import com.example.projectmanager_android.DB.Board;
import com.example.projectmanager_android.databinding.ActivityBoardBinding;

public class BoardActivity extends AppCompatActivity {

    ActivityBoardBinding mActivityBoardBinding;
    ImageButton mExitButton;
    TextView mBoardTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityBoardBinding = ActivityBoardBinding.inflate(getLayoutInflater());
        setContentView(mActivityBoardBinding.getRoot());

        mExitButton = mActivityBoardBinding.boardActivityExitButton;
        mBoardTitleTextView = mActivityBoardBinding.boardActivityBoardHeader;
        Board currentBoard = AppDataBase.getInstance(this).BoardDAO().getBoardByBoardId(SharedPreferencesHelper.getCurrentBoardId());
        if(currentBoard != null){
            System.out.println(currentBoard.getTitle());
            mBoardTitleTextView.setText(currentBoard.getTitle());
        }

        mExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Signals that the user is no longer in a BoardActivity.
                SharedPreferencesHelper.setCurrentBoardId(-1);

                Intent intent = LandingPageActivity.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });

    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, BoardActivity.class);
        return intent;
    }
}