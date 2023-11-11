package com.example.projectmanager_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        mUserDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .UserDAO();

        mUserCredentialsList = mUserDAO.getUsers();
        if(mUserCredentialsList.isEmpty()){
            Users testUser1 = AppDataBase.createUser("testUser1", "testUser1", false);
            Users admin2 = AppDataBase.createUser("admin2", "admin2", true);
            mUserDAO.insert(testUser1, admin2);

            mUserCredentialsList = mUserDAO.getUsers();
            System.out.println("Inserted predefined Users!");
        }

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
                    Intent intent = LandingPageActivity.getIntent(getApplicationContext());
                    startActivity(intent);
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