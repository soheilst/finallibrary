package com.keylid.keylidsdk;

import android.content.Context;
import net.jhoobin.jhub.CharkhoneSdkApp;

public class Force {
    public Force(Context context) {
        CharkhoneSdkApp.initSdk(context, getSecret(context));

    }

    public String[] getSecret(Context context) {
        return context.getResources().getStringArray(R.array.secrets);
    }
}
