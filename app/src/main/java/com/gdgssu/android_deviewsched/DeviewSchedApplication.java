package com.gdgssu.android_deviewsched;

import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;

public class DeviewSchedApplication extends Application{

    public static Context GLOBAL_CONTEXT;

    @Override
    public void onCreate() {
        super.onCreate();

        GLOBAL_CONTEXT = getApplicationContext();

        FacebookSdk.sdkInitialize(GLOBAL_CONTEXT);
    }
}
