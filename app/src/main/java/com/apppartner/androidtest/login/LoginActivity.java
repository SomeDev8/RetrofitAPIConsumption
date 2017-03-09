package com.apppartner.androidtest.login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apppartner.androidtest.bus.BusProvider;
import com.apppartner.androidtest.Communicator;
import com.apppartner.androidtest.server.ErrorResponse;
import com.apppartner.androidtest.R;
import com.apppartner.androidtest.server.ServerEvent;

import com.squareup.otto.Subscribe;


/**
 * Username and password are passed to Communicator instance and POST execution method is invoked from Communicator class.
 * Handler methods assess data sent via ServerEvent and create AlertDialog object for user with Server response
 */
public class LoginActivity extends AppCompatActivity {

    //==============================================================================================
    // Class Properties
    //==============================================================================================
    private EditText mUsernameInput;
    private EditText mPasswordInput;
    private String mUsername;
    private String mPassword;
    private Button mLoginButton;
    private AlertDialog.Builder mAlertDialog;
    private static final String TAG = "LoginActivity";

    //==============================================================================================
    // Static Class Methods
    //==============================================================================================
    public static void start(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);
        context.startActivity(starter);
    }
    //==============================================================================================
    // Lifecycle Methods
    //==============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.Login_title));
        setContentView(R.layout.activity_login);
        mUsernameInput = (EditText) findViewById(R.id.username_input);
        mPasswordInput = (EditText) findViewById(R.id.password_input);
        mLoginButton = (Button) findViewById(R.id.login);
        initButton();
        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

   }

    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    private void initButton() {
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLogin(v);
            }
        });
    }

    public void startLogin(View v) {
        mUsername = mUsernameInput.getText().toString();
        mPassword = mPasswordInput.getText().toString();

        Communicator communicator = new Communicator("POST",mUsername, mPassword);
        communicator.executeRequest();
    }

    @Subscribe
    public void onServerEvent(ServerEvent serverEvent) {
        if (serverEvent.getServerResponse() != null) {
            String message = serverEvent.getServerResponse().getMessage();
            String responseCode = serverEvent.getServerResponse().getResponseCode();
            long responseTime = serverEvent.getResponseTime();
            mAlertDialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialog));
            mAlertDialog
                    .setTitle("Server Response")
                    .setMessage("Code: " + responseCode + "\n"
                            + "Message: " + message + "\n"
                            + "Response Time: " + responseTime
                            + " ms")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .show();
            Log.e(TAG, "Server Response: " + message + " Response Code: " + responseCode + " " + responseTime + " ms");

        } else {
            Toast.makeText(getApplicationContext(), "invalid credentials", Toast.LENGTH_LONG).show();
        }
    }

    @Subscribe
    public void onErrorEvent(ErrorResponse errorResponse) {
        String errorMessage = errorResponse.getErrorMessage();
        Log.e(TAG, "Server Response: " + errorMessage);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return false;
    }
}