package com.example.projectmanager_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectmanager_android.DB.AppDataBase;
import com.example.projectmanager_android.DB.User;
import com.example.projectmanager_android.DB.UserDAO;
import com.example.projectmanager_android.databinding.ActivityLogInBinding;

import java.util.List;

public class LogInActivity extends AppCompatActivity {

    ActivityLogInBinding mLogInBinding;

    ImageButton mBackButton;

    EditText mUsernameText;
    EditText mPasswordText;

    TextView mUsernameAlertText;
    TextView mPasswordAlertText;

    Button mLogInButton;

    UserDAO mUserDAO;

    List<User> mUserCredentialsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mLogInBinding = ActivityLogInBinding.inflate(getLayoutInflater());
        setContentView(mLogInBinding.getRoot());

        mUsernameText = mLogInBinding.editTextUsername;
        mPasswordText = mLogInBinding.editTextPassword;
        mUsernameAlertText = mLogInBinding.alertTextUsername;
        mPasswordAlertText = mLogInBinding.alertTextPassword;
        mBackButton = mLogInBinding.logInActivityBackButton;
        mLogInButton = mLogInBinding.logInButton;

        mUserDAO = AppDataBase.getInstance(this).UserDAO();

        mUserCredentialsList = mUserDAO.getAllUsers();

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
               User user = findUserByCredentials();
               if(user != null){
                   logInUser(user);
               }
            }
        });
    }

    private boolean isUsernameInputFilled(){
        String username = mUsernameText.getText().toString();
        return !username.isEmpty();
    }

    private boolean isPasswordInputFilled(){
        String password = mPasswordText.getEditableText().toString();
        return !password.isEmpty();
    }

    private User findUserByCredentials(){
        boolean isUsernameInputted = isUsernameInputFilled();
        boolean isPasswordInputted = isPasswordInputFilled();

        boolean isUsernameFound = false;
        boolean isPasswordFound = false;
        User user = null;

        if(!isUsernameInputted){
            // Case: Username not inputted
            mUsernameAlertText.setText(R.string.emptyUsernameField_alertText);
            mUsernameAlertText.setVisibility(View.VISIBLE);
            mPasswordAlertText.setVisibility(View.GONE);
            return null;
        }
        else if(!isPasswordInputted){
            // Case: Username inputted; password not inputted.
            mPasswordAlertText.setText(R.string.emptyPasswordField_alertText);
            mPasswordAlertText.setVisibility(View.VISIBLE);
            mUsernameAlertText.setVisibility(View.GONE);
            // Return statement left out intentionally.
            // Still important to alert user if password is unavailable
        }

        String username = mUsernameText.getText().toString();
        String password = mPasswordText.getText().toString();

        for (User existingUser: mUserCredentialsList) {
            if(username.equals(existingUser.getUsername())){
                if(password.equals(existingUser.getPassword())){
                    // Case: input matches a User's credentials; Start the landing activity for the user that has logged in
                    isUsernameFound = true;
                    isPasswordFound = true;
                    user = existingUser;
                    break;
                }else{
                    // Case: Username exists, but password is incorrect
                    isUsernameFound = true;
                    isPasswordFound = false;
                }
            }else{
                isUsernameFound = false;
                isPasswordFound = false;
            }

        }

        if(!isUsernameFound){
            // Case: Username not found; password irrelevant
            mUsernameAlertText.setText(R.string.username_doesNotExist_alertText);
            mUsernameAlertText.setVisibility(View.VISIBLE);
            mPasswordAlertText.setVisibility(View.GONE);
        }
        else{
            if(!isPasswordFound){
                // Case: Username found; password not matching.
                System.out.println("User found; password incorrect");
                mUsernameAlertText.setVisibility(View.GONE);
                mPasswordAlertText.setText(R.string.password_incorrect_alertText);
                mPasswordAlertText.setVisibility(View.VISIBLE);
            }
            else{
                // Case: Username found; password does match.
                System.out.println("User found; password found");
                mUsernameAlertText.setVisibility(View.GONE);
                mPasswordAlertText.setVisibility(View.GONE);
                return user;
            }
        }
        return null;
    }

    private void logInUser(User user){
        addUserToSharedPreferences(user);
        startLandingPageActivity();
    }

    private void startLandingPageActivity(){
        Intent intent = LandingPageActivity.getIntent(getApplicationContext());
        startActivity(intent);

    }

    private void addUserToSharedPreferences(User user){
        SharedPreferences sharedUserPrefs = getSharedPreferences(String.valueOf(R.string.LoggedInUser_prefs), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedUserPrefs.edit();
        editor.putInt(SharedPreferencesHelper.CURRENT_USER_ID_KEY, user.getUserId());
        editor.putString(SharedPreferencesHelper.CURRENT_USER_USERNAME_KEY, user.getUsername());
        editor.putBoolean(SharedPreferencesHelper.CURRENT_USER_IS_ADMIN_KEY, user.getIsAdmin());
        System.out.println("Stored user info in SharedPreferences: \n" + user );
        editor.apply();
        Toast.makeText(LogInActivity.this, "Log In ID saved. ", Toast.LENGTH_LONG).show();
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