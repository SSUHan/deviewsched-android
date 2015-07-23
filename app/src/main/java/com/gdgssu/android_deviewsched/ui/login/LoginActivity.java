package com.gdgssu.android_deviewsched.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.gdgssu.android_deviewsched.R;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();

        initView();
    }

    private void initView() {
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "user_friends"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("Login", "onSuccess");

                //Todo MainActivity / HomeFragment로 넘어가는 로직 개발.
            }

            @Override
            public void onCancel() {
                Log.d("Login", "onCancel");

            }

            @Override
            public void onError(FacebookException e) {
                Log.d("Login", "onError"+e.toString());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
