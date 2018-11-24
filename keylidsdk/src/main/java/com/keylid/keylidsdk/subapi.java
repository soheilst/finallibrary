package com.keylid.keylidsdk;

import android.content.Context;
import android.util.Log;
import com.android.billingclient.util.IabHelper;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;

public class subapi {
    public String msg;
    public Context context;
    public String Sku;
    public String payload;
    public int RC_REQUEST;
    public String base64st;
    public Charkh ch;
    final String[] optid = {""};
    public publicvar s=new publicvar();
    public interface OKHttpNetwork{
        void onSuccess(String body);
        void onFailure(String body);
    }
    public subapi(publicvar p,Charkh charkh) {
        ch = charkh;
        s = p;
        msg = s.Message[0];
        context = s.context;
        Sku = s.Sku;
        payload = s.payload;
        RC_REQUEST = s.RC_REQUEST;
        base64st=s.base64st;

    }
    public  void sub(final String Servicename, final String Token, final String Msisdn
                     //,String userid
            , final OKHttpNetwork okHttpCallBack) {
        Pattern p = Pattern.compile("^(0|98|0098|\\Q+98\\E)?9(1|9)[0-9]*");
        Pattern I=  Pattern.compile("^(0|98|0098|\\Q+98\\E)?9(4|3|0)[0-9]*");
        Matcher mci = p.matcher(Msisdn);
        Matcher mtn=I.matcher(Msisdn);
        boolean b = mci.matches();
        boolean c=mtn.matches();
        Log.i(TAG, "msg is "+s.msgprogress);

        if (b) {
            if(s.msgprogress!="in progress") {
                s.msgprogress = "in progress";
                String Url = "http://api.keylid.com/v3/sub";
                Url += "/" + Servicename + "/" + Token + "/" + Msisdn;
                //+"/"+userid;
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(180, TimeUnit.SECONDS)
                        .writeTimeout(180, TimeUnit.SECONDS)
                        .readTimeout(180, TimeUnit.SECONDS)
                        .build();
                Request request = new Request.Builder()
                        .url(Url)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            JSONObject jresponse = null;
                            s.msgprogress = "progress end";
                            try {
                                jresponse = new JSONObject(response.body().string());
                                JSONObject sys = jresponse.getJSONObject("data");
                                s.opt[0] = jresponse.getString("OptId");
                                s.Status[0] = jresponse.getString("Status");
                                s.Message[0] = jresponse.getString("Message");
                                s.Subscribed[0] = sys.getString("Subscribed");
                                okHttpCallBack.onSuccess("{ Status: " + s.Status[0] + ",Message: " + s.Message[0] + ",Subscribed: " + s.Subscribed[0] + " }");
                            } catch (JSONException e) {
                                okHttpCallBack.onSuccess("Internal Server Error");
                            }
                        } else {
                            okHttpCallBack.onSuccess("Fail");
                            s.msgprogress = "progress end";
                        }
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        okHttpCallBack.onFailure("Internal Server Error");
                        s.msgprogress = "progress end";
                    }
                });
            }
            else {
                okHttpCallBack.onFailure("waite for operator ");
                okHttpCallBack.onSuccess("waite for operator ");
            }
        }
        if (c){


             ch.mchar();
        }

    }
    public  void confirm(String Servicename,String Token,String Msisdn,String cfcode
                         //,String userid
            ,final OKHttpNetwork okHttpCallBack){
        Pattern p = Pattern.compile("^(0|98|0098|\\Q+98\\E)?9(1|9)[0-9]*");
        Matcher mci = p.matcher(Msisdn);
        boolean b = mci.matches();
        Log.i(TAG, "msg is "+s.msgprogress);

        if (b) {
            if(s.msgprogress!="in progress") {
                s.msgprogress = "in progress";
                String Url="http://api.keylid.com/v3/confirm/";
                Url+="/"+Servicename+"/"+Token+"/"+Msisdn+"/"+cfcode+
                        //"/"+userid+
                        "?"+s.opt[0];
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(180, TimeUnit.SECONDS)
                        .writeTimeout(180, TimeUnit.SECONDS)
                        .readTimeout(180, TimeUnit.SECONDS)
                        .build();
                Request request = new Request.Builder()
                        .url(Url)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            JSONObject jresponse = null;
                            s.msgprogress = "progress end";
                            try {
                                jresponse = new JSONObject(response.body().string());
                                JSONObject sys = jresponse.getJSONObject("data");
                                s.opt[0] = jresponse.getString("OptId");
                                s.Status[0] = jresponse.getString("Status");
                                s.Message[0] = jresponse.getString("Message");
                                s.Subscribed[0] = sys.getString("Subscribed");
                                okHttpCallBack.onSuccess("{ Status: " + s.Status[0] + ",Message: " + s.Message[0] + ",Subscribed: " + s.Subscribed[0] + " }");
                            } catch (JSONException e) {
                                okHttpCallBack.onSuccess("Internal Server Error");
                            }
                        } else {
                            okHttpCallBack.onSuccess("Fail");
                            s.msgprogress = "progress end";
                        }
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        okHttpCallBack.onFailure("Internal Server Error");
                        s.msgprogress = "progress end";
                    }
                });
            }
            else {
                okHttpCallBack.onFailure("waite for operator ");
                okHttpCallBack.onSuccess("waite for operator ");
            }
        }


    }
    public  void unsub(String Servicename,String Token,String Msisdn
                       //,String userid
            ,final OKHttpNetwork okHttpCallBack) {
        Pattern p = Pattern.compile("^(0|98|0098|\\Q+98\\E)?9(1|9)[0-9]*");
        Matcher mci = p.matcher(Msisdn);
        boolean b = mci.matches();
        Log.i(TAG, "msg is "+s.msgprogress);
        if (b) {
            if(s.msgprogress!="in progress") {
                s.msgprogress = "in progress";
                String Url = "http://api.keylid.com/v3/unsub/";
                Url += "/" + Servicename + "/" + Token + "/" + Msisdn;
                //+"/"+userid;
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(180, TimeUnit.SECONDS)
                        .writeTimeout(180, TimeUnit.SECONDS)
                        .readTimeout(180, TimeUnit.SECONDS)
                        .build();
                Request request = new Request.Builder()
                        .url(Url)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            JSONObject jresponse = null;
                            s.msgprogress = "progress end";
                            try {
                                jresponse = new JSONObject(response.body().string());
                                JSONObject sys = jresponse.getJSONObject("data");
                                s.opt[0] = jresponse.getString("OptId");
                                s.Status[0] = jresponse.getString("Status");
                                s.Message[0] = jresponse.getString("Message");
                                s.Subscribed[0] = sys.getString("Subscribed");
                                okHttpCallBack.onSuccess("{ Status: " + s.Status[0] + ",Message: " + s.Message[0] + ",Subscribed: " + s.Subscribed[0] + " }");
                            } catch (JSONException e) {
                                okHttpCallBack.onSuccess("Internal Server Error");
                            }
                        } else {
                            okHttpCallBack.onSuccess("Fail");
                            s.msgprogress = "progress end";
                        }
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        okHttpCallBack.onFailure("Internal Server Error");
                        s.msgprogress = "progress end";
                    }
                });
            }
            else {
                okHttpCallBack.onFailure("waite for operator ");
                okHttpCallBack.onSuccess("waite for operator ");
            }
        }

    }
    public  void getstatus(String Servicename,String Token,String Msisdn
                           // ,String userid
            ,final OKHttpNetwork okHttpCallBack) {
        Pattern p = Pattern.compile("^(0|98|0098|\\Q+98\\E)?9(1|9)[0-9]*");
        Matcher mci = p.matcher(Msisdn);
        boolean b = mci.matches();
        Log.i(TAG, "msg is " + s.msgprogress);
        if (b) {
            if (s.msgprogress != "in progress") {
                s.msgprogress = "in progress";
                String Url = "http://api.keylid.com/v3/status/";
                Url += "/" + Servicename + "/" + Token + "/" + Msisdn;
                //+"/"+userid;
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(180, TimeUnit.SECONDS)
                        .writeTimeout(180, TimeUnit.SECONDS)
                        .readTimeout(180, TimeUnit.SECONDS)
                        .build();
                Request request = new Request.Builder()
                        .url(Url)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            JSONObject jresponse = null;
                            s.msgprogress = "progress end";
                            try {
                                jresponse = new JSONObject(response.body().string());
                                JSONObject sys = jresponse.getJSONObject("data");
                                s.opt[0] = jresponse.getString("OptId");
                                s.Status[0] = jresponse.getString("Status");
                                s.Message[0] = jresponse.getString("Message");
                                s.Subscribed[0] = sys.getString("Subscribed");
                                okHttpCallBack.onSuccess("{ Status: " + s.Status[0] + ",Message: " + s.Message[0] + ",Subscribed: " + s.Subscribed[0] + " }");
                            } catch (JSONException e) {
                                okHttpCallBack.onSuccess("Internal Server Error");
                            }
                        } else {
                            okHttpCallBack.onSuccess("Fail");
                            s.msgprogress = "progress end";
                        }
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        okHttpCallBack.onFailure("Internal Server Error");
                        s.msgprogress = "progress end";
                    }
                });
            } else {
                okHttpCallBack.onFailure("waite for operator ");
                okHttpCallBack.onSuccess("waite for operator ");
            }
        }
    }
}
