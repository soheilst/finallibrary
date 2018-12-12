package com.keylid.keylidsdk;

import android.content.Context;
import android.util.Log;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;

public class subapi {
    final String[] optid = {""};
    public String msg;
    public Context context;
    public String Sku;
    public String payload;
    public int RC_REQUEST;
    public String base64st;
    public Charkh ch;
    public String tok;
    public String pk;
    public interface OKHttpNetwork {
        void onSuccess(String body);

        void onFailure(String body);
    }



    public publicvar s ;

    public subapi(publicvar p, Charkh charkh) {
        ch = charkh;
        s = p;
        msg = s.Message[0];
        context = s.context;
        Sku = s.Sku;
        payload = s.payload;
        RC_REQUEST = s.RC_REQUEST;
        base64st = s.base64st;
        s.Token=tok;
        s.Pakagename=pk;

    }


    public void sub(final String Servicename, final String Token, final String Msisdn
                    //,String userid
            , final OKHttpNetwork okHttpCallBack) {
        String reg = regex(Msisdn);
        Log.i(TAG, "msg is " + s.msgprogress);

        if (reg == "MCI") {
            if (s.msgprogress != "in progress") {
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
            } else {
                okHttpCallBack.onFailure("waite for operator ");
                okHttpCallBack.onSuccess("waite for operator ");
            }
        }
        if (reg == "MTN") {


            ch.mchar();
        }

    }

    public void confirm(String Servicename, String Token, String Msisdn, String cfcode
                        //,String userid
            , final OKHttpNetwork okHttpCallBack) {

        String reg = regex(Msisdn);
        if (reg == "MCI") {
            if (s.msgprogress != "in progress") {
                s.msgprogress = "in progress";
                String Url = "http://api.keylid.com/v3/confirm/";
                Url += "/" + Servicename + "/" + Token + "/" + Msisdn + "/" + cfcode +
                        //"/"+userid+
                        "?" + s.opt[0];
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

    public void unsub(String Servicename, final String Token, String Msisdn
                      //,String userid
            , final OKHttpNetwork okHttpCallBack) {
        String reg = regex(Msisdn);
        if (reg == "MCI") {
            if (s.msgprogress != "in progress") {
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
                                //s.opt[0] = jresponse.getString("OptId");
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
        if (reg == "MTN") {
        Charkh.Listener4 lst=new Charkh.Listener4() {
            @Override
            public void onsuccesstoken(String pkj, String Tkn) {
                String g=pkj;
                String l=Tkn;
                String Url = "http://185.12.103.39/charkhoone/unsub.php?token=";
                Url += Tkn + ":cancel&sku=" + Sku + "&package=" + pkj;
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
                            try {
                                jresponse = new JSONObject(response.body().string());
                                //JSONObject sys = jresponse.getJSONObject("data");
                                // s.opt[0] = jresponse.getString("OptId");
                                //s.Status[0] = jresponse.getString("Status");
                                s.Message[0] = jresponse.getString("error_description");
                                //s.Subscribed[0] = sys.getString("Subscribed");
                                okHttpCallBack.onSuccess("{ Message: " + s.Message[0] + " }");
                            } catch (JSONException e) {
                                okHttpCallBack.onSuccess(e.toString());
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

        };
        ch.resListener4(lst);
            ch.unsubchar();
            /*
            String Url = "http://185.12.103.39/charkhoone/unsub.php?token=";
            Url += tok + ":cancel&sku=" + Sku + "&package=" + pk;
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
                        try {
                            jresponse = new JSONObject(response.body().string());
                            JSONObject sys = jresponse.getJSONObject("data");
                            // s.opt[0] = jresponse.getString("OptId");
                            //s.Status[0] = jresponse.getString("Status");
                            s.Message[0] = jresponse.getString("error_description");
                            //s.Subscribed[0] = sys.getString("Subscribed");
                            okHttpCallBack.onSuccess("{ Message: " + s.Message[0] + " }");
                        } catch (JSONException e) {
                            okHttpCallBack.onSuccess(e.toString());
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
            */
        }


    }


    public void getstatus(String Servicename, String Token, String Msisdn
                          // ,String userid
            , final OKHttpNetwork okHttpCallBack) {
        String reg = regex(Msisdn);
        if (reg == "MCI") {
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
                               // s.opt[0] = jresponse.getString("OptId");
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
        if (reg == "MTN") {
            Charkh.Listener2 listener = new Charkh.Listener2() {
                @Override
                public void onSuccess(String msg) {
                    okHttpCallBack.onSuccess("Message:"+msg);
                }

                @Override
                public void onFailure(String msg) {
                    okHttpCallBack.onSuccess("Message:"+msg);
                }

                @Override
                public void onsuccesstoken(String msg2) {

                }
            };
            ch.resListener2(listener);
            ch.statuschar();
        }
    }

    public String regex(String msisdn) {
        Pattern p = Pattern.compile("^(0|98|0098|\\Q+98\\E)?9(1|9)[0-9]*");
        Pattern I = Pattern.compile("^(0|98|0098|\\Q+98\\E)?9(4|3|0)[0-9]*");
        Matcher mci = p.matcher(msisdn);
        Matcher mtn = I.matcher(msisdn);
        boolean b = mci.matches();
        boolean c = mtn.matches();
        if (b) {
            return "MCI";
        }
        if (c) {
            return "MTN";
        }
        return "Not Match";
    }



}
