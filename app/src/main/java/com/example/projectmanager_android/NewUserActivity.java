package com.example.projectmanager_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class NewUserActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private ImageButton mExitButton;
    private EditText mUsernameText;
    private EditText mPasswordText;
    private EditText mReEnteredPasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        mExitButton = findViewById(R.id.newUserActivity_exit_button);
        mUsernameText = findViewById(R.id.newUserActivity_username_text);
        mPasswordText = findViewById(R.id.newUserActivity_password_text);
        mReEnteredPasswordText = findViewById(R.id.newUserActivity_reEnteredPassword_text);

        mExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MainActivity.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });

    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, NewUserActivity.class);
        return intent;
    }

}