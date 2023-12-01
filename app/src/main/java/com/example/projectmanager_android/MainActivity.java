package com.example.projectmanager_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projectmanager_android.DB.UserViewModel;
import com.example.projectmanager_android.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mActivityMainBinding;
    Button mLogInButton;
    Button mSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initializing the SharedPrefsHelper
        if(!SharedPreferencesHelper.isUserPrefsSet()){
            setSharedUserPrefs();
        }

        // Checking if the user is logged in. If so, begins the Landing page activity.
        userLoggedInCheck();

        setContentView(R.layout.activity_main);

        mActivityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mActivityMainBinding.getRoot());

        mLogInButton = mActivityMainBinding.mainActivityLoginButton;
        mSignUpButton = mActivityMainBinding.mainActivitySignUpButton;

        mLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = LogInActivity.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = NewUserActivity.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });

    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    public void setSharedUserPrefs(){
        SharedPreferences sharedPrefs = getSharedPreferences(String.valueOf(R.string.LoggedInUser_prefs), MODE_PRIVATE);
        SharedPreferencesHelper.setUserPrefs(sharedPrefs);
    }

    private void userLoggedInCheck(){
        if(SharedPreferencesHelper.isUserLoggedIn()){
            Intent intent = LandingPageActivity.getIntent(getApplicationContext());
            startActivity(intent);
        }
    }
}