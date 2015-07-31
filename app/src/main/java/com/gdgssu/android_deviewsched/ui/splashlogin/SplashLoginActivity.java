package com.gdgssu.android_deviewsched.ui.splashlogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.gdgssu.android_deviewsched.DeviewSchedApplication;
import com.gdgssu.android_deviewsched.R;
import com.gdgssu.android_deviewsched.helper.LoginPreferenceHelper;
import com.gdgssu.android_deviewsched.ui.MainActivity;

public class SplashLoginActivity extends AppCompatActivity {

    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashlogin);

        callbackManager = CallbackManager.Factory.create();

        if (DeviewSchedApplication.LOGIN_STATE){
            finish();
            startActivity(new Intent(SplashLoginActivity.this, MainActivity.class));
        }


        initView();
    }

    private void initView() {
        final LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_friends");
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
//                        Log.d("Login", "onSuccess");
//
//                        Log.d("Token", AccessToken.getCurrentAccessToken().getToken());
//
//                        //Todo 서버 측으로 Volley-Extenstion을 이용해 token을 post형식으로 보내는 로직 개발 with Volley Extension
//                        Volleyer.volleyer().post("http://www.google.com")
//                                .addHeader("token", AccessToken.getCurrentAccessToken().getToken())
//                                .withListener(new Response.Listener<String>() {
//                                    @Override
//                                    public void onResponse(String response) {
//                                        //Todo MainActivity / HomeFragment로 넘어가는 로직 개발.
//                                    }
//                                })
//                                .withErrorListener(new Response.ErrorListener() {
//                                    @Override
//                                    public void onErrorResponse(VolleyError error) {
//
//                                    }
//                                })
//                                .execute();

                        LoginPreferenceHelper prefHelper = new LoginPreferenceHelper(DeviewSchedApplication.GLOBAL_CONTEXT);
                        prefHelper.setPrefLoginValue(LoginPreferenceHelper.PREF_LOGIN_STATE, true);
                        finish();
                        startActivity(new Intent(SplashLoginActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onCancel() {
                        Log.d("Login", "onCancel");

                    }

                    @Override
                    public void onError(FacebookException e) {
                        Log.d("Login", "onError" + e.toString());
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
