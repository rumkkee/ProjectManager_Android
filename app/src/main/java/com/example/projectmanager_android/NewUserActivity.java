package com.example.projectmanager_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewUserActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        mEditText = findViewById(R.id.newUserActivity_username_text);

        final Button saveButton = findViewById(R.id.newUserActivity_createAccount_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(mEditText.getText())){
                    setResult(RESULT_CANCELED, replyIntent);
                }
                else{
                    String user = mEditText.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, user);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}