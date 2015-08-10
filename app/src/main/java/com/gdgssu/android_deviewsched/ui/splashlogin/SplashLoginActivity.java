package com.gdgssu.android_deviewsched.ui.splashlogin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.gdgssu.android_deviewsched.DeviewSchedApplication;
import com.gdgssu.android_deviewsched.R;
import com.gdgssu.android_deviewsched.helper.LoginPreferenceHelper;
import com.gdgssu.android_deviewsched.model.AllScheItems;
import com.gdgssu.android_deviewsched.ui.MainActivity;

import static com.navercorp.volleyextensions.volleyer.Volleyer.volleyer;

public class SplashLoginActivity extends AppCompatActivity implements FacebookCallback<LoginResult> {

    private static final String TAG = "SplashLoginActivity";

    private CallbackManager callbackManager;

    private LinearLayout frontInfo;

    /**
     * 애플리케이션에 유저 로그인이 되어있는 상태와 되어있지 않은 상태로 액티비티에서 하는 일이 달라짐
     * DeviewSchedApplication.LOGIN_STATE로 데뷰 앱에 로그인이 되어있는 상태를 받아왔을 떄,
     *
     * 로그인이 안되어 있는 상태(초기 사용자) :
     *      애플리케이션이 시작되고 이 화면이 뜨고나서 전체 시간표를 받아옴.
     *      전체시간표를 모두 받아오면 GDG SSU로고가 사라지고, 페이스북으로 로그인 버튼이 나온다.
     *      페이스북으로 로그인 버튼을 누르고 onSuccess가 떨어지면 서버에 유저를 등록시키기 위한 AccessToken을 전달하고
     *      유저의 사진과 실명을 가져온다.
     *      메인화면에 보여줄 데이터도 가져온다.
     *
     * 로그인이 되어있는 상태 :
     *      전체 시간표 / 메인화면에 뿌려질 데이터 / 유저의 사진과 실명 데이터를 가져옴
     *      애플리케이션이 시작하고 3초뒤에 페이스북으로 로그인 버튼이 뜨지 않는다.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashlogin);

        initView();

        getAllScheData();

        callbackManager = CallbackManager.Factory.create();

        if (DeviewSchedApplication.LOGIN_STATE){
            finish();
            startActivity(new Intent(SplashLoginActivity.this, MainActivity.class));
        }else{

        }
    }

    private void initView() {
        final LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_friends");
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.registerCallback(callbackManager, SplashLoginActivity.this);
            }
        });

        frontInfo = (LinearLayout)findViewById(R.id.activity_splashlogin_frontinfo);

    }

    private void getAllScheData() {
        /**
         * Todo 이곳에서 모든 전체스케쥴의 데이터를 가져오는 일을 해야함.
         */
        volleyer(DeviewSchedApplication.deviewRequestQueue)
                .get(DeviewSchedApplication.HOST_URL + "mock/allsche.json")
                .withTargetClass(AllScheItems.class)
                .withListener(new Response.Listener<AllScheItems>() {
                    @Override
                    public void onResponse(AllScheItems items) {
                        AllScheItems.result = items;

                        frontInfoFadeout();
                    }
                })
                .withErrorListener(new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, error.toString());
                    }
                })
                .execute();
    }

    private void frontInfoFadeout() {
        final Animation animationFadeout;

        animationFadeout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout);
        animationFadeout.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                frontInfo.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        frontInfo.startAnimation(animationFadeout);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

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
}
