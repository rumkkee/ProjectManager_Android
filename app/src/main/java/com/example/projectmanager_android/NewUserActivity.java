package com.example.projectmanager_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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

    private TextView mUsernameAlertText;
    private TextView mPasswordAlertText;
    private TextView mReEnteredPasswordAlertText;

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

        mUsernameAlertText = findViewById(R.id.newUserActivity_username_alertText);
        mPasswordAlertText = findViewById(R.id.newUserActivity_password_alertText);
        mReEnteredPasswordAlertText = findViewById(R.id.newUserActivity_reEnteredPassword_alertText);


        mExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainActivity();
            }
        });

        mCreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidCredentials()){
                    createUser();
                    startMainActivity();
                }
            }
        });

    }

    private boolean isValidCredentials(){
        // This method is intentionally structured to check each field, regardless if one already returned false.
        // This is so that all text fields can potentially display their alerts.
        boolean isValid = true;
        if(!isValidUsername()){
            isValid = false;
            System.out.println("Failed Username check");
        }
        if(!isValidPassword()){
            isValid = false;
            System.out.println("Failed password check");
        }
        if(!isValidReEnteredPassword()){
            System.out.println("Failed re-entered password check");
            isValid = false;
        }
        return isValid;
    }

    private boolean isValidUsername(){
        boolean isValid = true;
        String usernameInput = mUsernameText.getEditableText().toString();
        if(usernameInput.isEmpty()){
            mUsernameAlertText.setText(R.string.emptyField_alertText);
            mUsernameAlertText.setVisibility(View.VISIBLE);
            isValid = false;
        }
        else{
            // Compares the username for availability by comparing it to each existing User's username
            for (User user : mAllUsers){
                if(usernameInput.equals(user.getUsername())){
                    mUsernameAlertText.setText(R.string.username_unavailable_alertText);
                    mUsernameAlertText.setVisibility(View.VISIBLE);
                    isValid = false;
                }
            }
        }
        if(isValid){
            mUsernameAlertText.setVisibility(View.GONE);
        }
        return isValid;
    }

    private boolean isValidPassword(){
        boolean isValid = true;
        String passwordInput = mPasswordText.getEditableText().toString();

        if(passwordInput.isEmpty()){
            mPasswordAlertText.setText(R.string.emptyField_alertText);
            mPasswordAlertText.setVisibility(View.VISIBLE);
            isValid = false;
        }
        if(isValid){
            mPasswordAlertText.setVisibility(View.GONE);
        }
        return isValid;
    }

    /**
     * Determines if the reEnteredPassword field meets the sign-up criteria. <br><br>
     * Under given circumstances, will display custom messages if:
     * <li> this field is empty </li>
     * <li> the password field is empty </li>
     * <li> the two fields don't match. </li>
     * @return a boolean isValid
     */
    private boolean isValidReEnteredPassword(){
        boolean isValid = true;
        String passwordReEnteredInput = mReEnteredPasswordText.getEditableText().toString();
        String passwordInput = mPasswordText.getEditableText().toString();

        if(passwordReEnteredInput.isEmpty()){
            mReEnteredPasswordAlertText.setText(R.string.emptyField_alertText);
            mReEnteredPasswordAlertText.setVisibility(View.VISIBLE);
            isValid = false;
        }
        else{
            if(passwordInput.isEmpty()){
                mReEnteredPasswordAlertText.setText(R.string.emptyPasswordField_alertText);
                mReEnteredPasswordAlertText.setVisibility(View.VISIBLE);
                isValid = false;
            }
            else{
                // Will only compare this re-entered password field to the password field if both contain text.
                if(!passwordReEnteredInput.equals(passwordInput)){
                    mReEnteredPasswordAlertText.setText(R.string.passwordMismatch_alertText);
                    mReEnteredPasswordAlertText.setVisibility(View.VISIBLE);
                    isValid = false;
                }
            }
        }

        if(isValid){
            mReEnteredPasswordAlertText.setVisibility(View.GONE);
        }
        return isValid;
    }

    private void createUser(){
        User user = new User(mUsernameText.getEditableText().toString(), mPasswordText.getEditableText().toString());
        AppDataBase.getInstance(getApplicationContext()).UserDAO().insert(user);
        Toast.makeText(NewUserActivity.this, "Account Created! ", Toast.LENGTH_LONG).show();
    }

    private void startMainActivity(){
        Intent intent = MainActivity.getIntent(getApplicationContext());
        startActivity(intent);
    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, NewUserActivity.class);
        return intent;
    }



}