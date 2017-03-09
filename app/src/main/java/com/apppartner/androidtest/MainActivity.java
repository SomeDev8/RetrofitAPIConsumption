package com.apppartner.androidtest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.apppartner.androidtest.animation.AnimationActivity;
import com.apppartner.androidtest.chat.ChatActivity;
import com.apppartner.androidtest.login.LoginActivity;

/**
 * Submitted by Roshan Alexander
 */
public class MainActivity extends AppCompatActivity {

    //==============================================================================================
    // Class Properties
    //==============================================================================================
    private Button mChatButton;
    private Button mLoginButton;
    private Button mAnimationButton;

    //==============================================================================================
    // Static Class Methods
    //==============================================================================================
    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    //==============================================================================================
    // Lifecycle Methods
    //==============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.activity_main_title);
        setContentView(R.layout.activity_main);
        mChatButton = (Button) findViewById(R.id.chat_button);
        mLoginButton = (Button) findViewById(R.id.login_button);
        mAnimationButton = (Button) findViewById(R.id.animation_button);

        initButtons();
    }

    //==============================================================================================
    // Button Click Methods
    //==============================================================================================

    private void initButtons() {
        mChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChatClicked(v);
            }
        });

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginClicked(v);
            }
        });

        mAnimationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAnimationClicked(v);
            }
        });
    }

    public void onChatClicked(View v) {
        ChatActivity.start(this);
    }

    public void onLoginClicked(View v) {
        LoginActivity.start(this);
    }

    public void onAnimationClicked(View v) {
        AnimationActivity.start(this);
    }
}