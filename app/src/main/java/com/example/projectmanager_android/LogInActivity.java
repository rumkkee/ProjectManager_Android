package com.example.projectmanager_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.credentials.Credential;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectmanager_android.DB.AppDataBase;
import com.example.projectmanager_android.DB.Users;
import com.example.projectmanager_android.DB.UsersDAO;
import com.example.projectmanager_android.databinding.ActivityLogInBinding;

import java.util.List;

public class LogInActivity extends AppCompatActivity {

    ActivityLogInBinding mLogInBinding;

    Button mBackButton;

    EditText mUsername;
    EditText mPassword;

    Button mLogInButton;

    UsersDAO mUserDAO;

    List<Users> mUserCredentialsList;

    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);



        mLogInBinding = ActivityLogInBinding.inflate(getLayoutInflater());
        setContentView(mLogInBinding.getRoot());

        mUsername = mLogInBinding.editTextEmail;
        mPassword = mLogInBinding.editTextPassword;

        mBackButton = mLogInBinding.logInActivityBackButton;
        mLogInButton = mLogInBinding.logInButton;

        mSharedPreferences = getSharedPreferences(String.valueOf(R.string.LoggedInUser_prefs), MODE_PRIVATE);

        mUserDAO = AppDataBase.getInstance(this).UserDAO();

        mUserCredentialsList = mUserDAO.getUsers();

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
               logInRequest();
            }
        });
    }

    private void logInRequest(){
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();

        Users inputtedCredentials = new Users(username, password);

        for (Users credentials: mUserCredentialsList) {
            if(inputtedCredentials.getUsername().equals(credentials.getUsername())){
                if(inputtedCredentials.getPassword().equals(credentials.getPassword())){
                    // Credentials exist; Starts the landing activity for the user that has logged in
                    System.out.println("Log in credentials match!");
                    addUserToSharedPreferences(credentials);
                    startLandingPageActivity();
                }else{
                    // Case where the user exists, but password is incorrect
                    System.out.println("Password is incorrect!");
                }
            }else{
                // Case where the username doesn't exist
                System.out.println("Username doesn't exist!");
            }
        }

    }

    private void startLandingPageActivity(){
        Intent intent = LandingPageActivity.getIntent(getApplicationContext());
        startActivity(intent);

    }

    private void addUserToSharedPreferences(Users user){
        //mSharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt("currentUser_id", user.getUserId());
        editor.putString("currentUser_username", user.getUsername());
        editor.putBoolean("currentUser_isAdmin", user.getIsAdmin());
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