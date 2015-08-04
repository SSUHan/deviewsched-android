package com.gdgssu.android_deviewsched;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.facebook.FacebookSdk;
import com.gdgssu.android_deviewsched.helper.LoginPreferenceHelper;
import static com.navercorp.volleyextensions.volleyer.Volleyer.*;

import com.gdgssu.android_deviewsched.model.AllScheItems;
import com.navercorp.volleyextensions.volleyer.factory.DefaultRequestQueueFactory;

public class DeviewSchedApplication extends Application{

    public static Context GLOBAL_CONTEXT;
    public static Boolean LOGIN_STATE;
    public static final String HOST_URL = "http://deview.unstabler.pl/";

    private static final String TAG = "DeviewSchedApplication";

    public static RequestQueue deviewRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();

        GLOBAL_CONTEXT = getApplicationContext();
        FacebookSdk.sdkInitialize(GLOBAL_CONTEXT);
        setLoginState();
        initRequestQueue();
        getAllScheData();
    }

    public void setLoginState() {
        LoginPreferenceHelper prefHelper = new LoginPreferenceHelper(GLOBAL_CONTEXT);
        LOGIN_STATE = prefHelper.getPrefLoginValue(LoginPreferenceHelper.PREF_LOGIN_STATE, false);
    }

    private void initRequestQueue() {
        deviewRequestQueue = DefaultRequestQueueFactory.create(GLOBAL_CONTEXT);
        deviewRequestQueue.start();
    }

    private void getAllScheData() {
        /**
         * Todo 이곳에서 모든 전체스케쥴의 데이터를 가져오는 일을 해야함.
         */
        volleyer(deviewRequestQueue)
                .get(HOST_URL + "2014/list")
                .withTargetClass(AllScheItems.class)
                .withListener(new Response.Listener<AllScheItems>() {
                    @Override
                    public void onResponse(AllScheItems items) {

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
}
