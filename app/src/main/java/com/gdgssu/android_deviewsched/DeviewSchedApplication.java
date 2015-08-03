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
        LoginPreferenceHelper prefHelper = new LoginPreferenceHelper(GLOBAL_CONTEXT);
        LOGIN_STATE = prefHelper.getPrefLoginValue(LoginPreferenceHelper.PREF_LOGIN_STATE, false);

        FacebookSdk.sdkInitialize(GLOBAL_CONTEXT);

        deviewRequestQueue = DefaultRequestQueueFactory.create(GLOBAL_CONTEXT);
        deviewRequestQueue.start();

        volleyer(deviewRequestQueue)
                .get(HOST_URL + "2014/list")
                .withListener(new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response);
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
