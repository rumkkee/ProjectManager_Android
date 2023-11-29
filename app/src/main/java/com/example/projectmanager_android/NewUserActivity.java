package com.example.projectmanager_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.projectmanager_android.DB.AppDataBase;
import com.example.projectmanager_android.DB.User;

import java.util.List;

public class NewUserActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private ImageButton mExitButton;
    private EditText mUsernameText;
    private EditText mPasswordText;
    private EditText mReEnteredPasswordText;
    private Button mCreateAccountButton;

    List<User> mAllUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        mAllUsers = AppDataBase.getInstance(getApplicationContext()).UserDAO().getAllUsers();

        mExitButton = findViewById(R.id.newUserActivity_exit_button);
        mUsernameText = findViewById(R.id.newUserActivity_username_text);
        mPasswordText = findViewById(R.id.newUserActivity_password_text);
        mReEnteredPasswordText = findViewById(R.id.newUserActivity_reEnteredPassword_text);
        mCreateAccountButton = findViewById(R.id.newUserActivity_createAccount_button);



        mExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MainActivity.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });

        mCreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Check if the fields have been completed
                if(isValidCredentials()){

                }
            }
        });

    }

    private boolean isValidCredentials(){
        return isValidUsername() && isValidPassword();
    }

    private boolean isValidUsername(){
        String usernameInput = mUsernameText.getEditableText().toString();
        // Checking if username is empty
        if(usernameInput.isEmpty()){
            return false;
        }
        else{
            // Compares the username for availability by comparing it to each existing User's username
            for (User user : mAllUsers){
                if(usernameInput.equals(user.getUsername())){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValidPassword(){
        String passwordInput = mPasswordText.getEditableText().toString();
        String passwordReEnteredInput = mReEnteredPasswordText.getEditableText().toString();
        if(!passwordInput.isEmpty()){
            if(!passwordReEnteredInput.isEmpty()){
                if(passwordReEnteredInput.equals(passwordInput)){
                    return true;
                }
            }
        }
        return false;
    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, NewUserActivity.class);
        return intent;
    }

}