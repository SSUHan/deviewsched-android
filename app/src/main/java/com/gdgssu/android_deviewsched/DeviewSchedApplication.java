package com.gdgssu.android_deviewsched;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.gdgssu.android_deviewsched.helper.LoginPreferenceHelper;

public class DeviewSchedApplication extends Application{

    public static Context GLOBAL_CONTEXT;
    public static Boolean LOGIN_STATE;

    @Override
    public void onCreate() {
        super.onCreate();

        GLOBAL_CONTEXT = getApplicationContext();
        LoginPreferenceHelper prefHelper = new LoginPreferenceHelper(GLOBAL_CONTEXT);
        LOGIN_STATE = prefHelper.getPrefLoginValue(LoginPreferenceHelper.PREF_LOGIN_STATE, false);

        FacebookSdk.sdkInitialize(GLOBAL_CONTEXT);

    }
}
