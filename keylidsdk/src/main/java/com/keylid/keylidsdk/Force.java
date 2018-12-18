package com.keylid.keylidsdk;

import android.content.Context;
import android.util.Log;
import net.jhoobin.jhub.CharkhoneSdkApp;
import okhttp3.*;

import java.io.IOException;
import java.util.Collections;

import static android.content.ContentValues.TAG;

public class Force {
    public Force(Context context,String Pakagename) {
        CharkhoneSdkApp.initSdk(context, getSecret(context));
        MediaType JSON = MediaType.parse("application/json;charset=utf-8");
        RequestBody body = RequestBody.create(JSON, "{\n" +
                "  \"json\": {\n" +
                "  \t\"type\":18  \n" +
                "        }\n" +
                "}");
        String url = "https://analytics.keylid.com/event/add/";
        ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_2)
                .cipherSuites(
                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
                .build();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectionSpecs(Collections.singletonList(spec))
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Authorization", "token 017f853c2287846ca78677dfcac44e2e48a3de5b")
                .addHeader("Application",Pakagename)
                //.addHeader("Content-Type","application/json")
                // .connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS))
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "call keylid is fail"+"-->"+e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
            }
        });

    }

    public String[] getSecret(Context context) {
        return context.getResources().getStringArray(R.array.secrets);
    }
}
