package com.example.projectmanager_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.projectmanager_android.databinding.ActivityLandingPageBinding;

public class LandingPageActivity extends AppCompatActivity {

    ActivityLandingPageBinding mLandingPageBinding;

    Button mAddBoardButton;

    TextView mUserGreeting;

    Button mLogOutButton;

    SharedPreferences mSharedPreferences;

    Button mAdminButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLandingPageBinding = ActivityLandingPageBinding.inflate(getLayoutInflater());

        mAddBoardButton = mLandingPageBinding.addBoardButton;
        mLogOutButton = mLandingPageBinding.LogOutButton;
        mUserGreeting = mLandingPageBinding.userGreeting;

        mSharedPreferences = getSharedPreferences(String.valueOf(R.string.LoggedInUser_prefs), MODE_PRIVATE);
        setUserGreeting();
        adminCheck();

        setContentView(mLandingPageBinding.getRoot());

        mAddBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBoardSetupFragment();
            }
        });

        mLogOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MainActivity.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });
    }

    private void showBoardSetupFragment(){
        BoardSetupFragment boardSetupFragment = new BoardSetupFragment();

        getSupportFragmentManager().beginTransaction()
                .add(android.R.id.content, boardSetupFragment)
                .addToBackStack(null)
                .commit();
    }

    private void adminCheck() {
        boolean isAdmin = mSharedPreferences.getBoolean("currentUser_isAdmin", false);
        if(isAdmin){
            mAdminButton = mLandingPageBinding.adminButton;
            mAdminButton.setVisibility(View.VISIBLE);
        }
    }

    private void setUserGreeting(){
        String currentUsername = mSharedPreferences.getString("currentUser_username", "");
        mUserGreeting.setText(getString(R.string.userGreeting) + currentUsername);
    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, LandingPageActivity.class);
        //TODO: Have this intent take a boolean isAdmin
        // This is used to determine whether to enable admin features (a button, for now)
        return intent;
    }
}