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


    TextView mUserGreeting;

    Button mLogOutButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLandingPageBinding = ActivityLandingPageBinding.inflate(getLayoutInflater());


        mLogOutButton = mLandingPageBinding.LogOutButton;
        mUserGreeting = mLandingPageBinding.userGreeting;


        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.loggedInAccount_username_key), Context.MODE_PRIVATE);

        System.out.println("Username stored in shared prefs: " + sharedPref);
        mUserGreeting.setText("Hello, " + sharedPref);

        setContentView(mLandingPageBinding.getRoot());
        mLogOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MainActivity.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });
    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, LandingPageActivity.class);
        //TODO: Have this intent take a boolean isAdmin
        // This is used to determine whether to enable admin features (a button, for now)
        return intent;
    }
}