package com.example.projectmanager_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectmanager_android.databinding.ActivityLogInBinding;

public class LogInActivity extends AppCompatActivity {

    ActivityLogInBinding mLogInBinding;

    Button mBackButton;

    EditText mUsername;
    EditText mPassword;

    Button mLogInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLogInBinding = ActivityLogInBinding.inflate(getLayoutInflater());
        setContentView(mLogInBinding.getRoot());

        mUsername = mLogInBinding.editTextEmail;
        mPassword = mLogInBinding.editTextPassword;

        mBackButton = mLogInBinding.logInActivityBackButton;
        mLogInButton = mLogInBinding.logInButton;

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MainActivity.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });

        mLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: After connecting a ROOM database,
                //  check if the input matches an existing account.
                //  If true, take the user to their landing page.
            }
        });
    }

    /**
     * An intent factory used to start this activity (LogInActivity)
     * @param context the context for the current activity
     * @return an intent that will allow us to start the LogInActivity
     */
    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, LogInActivity.class);
        return intent;
    }

}