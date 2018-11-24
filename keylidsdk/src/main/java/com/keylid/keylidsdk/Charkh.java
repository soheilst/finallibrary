package com.keylid.keylidsdk;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.android.billingclient.util.*;

import static android.content.ContentValues.TAG;

public class Charkh {
    public publicvar pubvar;
    public IabHelper mhelper;
    public String msg;
    public Context context;
    public String Sku;
    public String payload;
    public int RC_REQUEST;

    public Charkh(publicvar p) {
        pubvar = p;
        msg = pubvar.Message[0];
        context = pubvar.context;
        Sku = pubvar.Sku;
        payload = pubvar.payload;
        RC_REQUEST = pubvar.RC_REQUEST;
    }


    public void mchar() {
        try {
            Log.d(TAG, "error111");

            mhelper.queryInventoryAsync(mGotInventoryListener);
            Log.d(TAG, "error1112");

        } catch (IabHelper.IabAsyncInProgressException e) {
            msg = e.toString();
            Log.d(TAG,msg);
        }


    }

    public void setup(Context context, String key) {

        Log.d(TAG, "step1");

        mhelper = new IabHelper(context, key, new MarketIntentFactorySDK(true));
        Log.d(TAG, "step2");


        boolean f = mhelper.subscriptionsSupported();
        if (!f) {
            Log.d(TAG, "step3");

        }
        mhelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {


            @Override
            public void onIabSetupFinished(IabResult Result) {

                if (!Result.isSuccess()) {
                    Log.d(TAG, "step4");

                    msg = "Error";
                    Log.d(TAG, "error2");

                    return;
                }
                if (mhelper == null) {
                    Log.d(TAG, "step5");

                    Log.d(TAG, "onIabSetupFinished");
                    msg = "onIabSetupFinished";
                    Log.d(TAG, "error3");

                    return;
                }
            }
        });
    }

    private IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
        @Override
        public void onQueryInventoryFinished(IabResult Result, Inventory inventory) {

            if (mhelper == null) {
                Log.d(TAG, "step6");
                return;
            }

            if (Result.isFailure()) {
                Log.d(TAG, "step7");

                Purchase purchase = inventory.getPurchase(pubvar.Sku);


//                if (purchase != null && verifyDeveloperPayload(purchase)) {
                try {

                    mhelper.launchPurchaseFlow((Activity) context, Sku, RC_REQUEST, mPurchaseFinishedListener, payload);
                    Log.d(TAG, "step8");

                } catch (IabHelper.IabAsyncInProgressException e) {
                    msg = e.toString();
                    Log.d(TAG, "step9");


                }

//                }
                msg = "Query Inventory :Finish";
                return;
            }


            Purchase purchase = inventory.getPurchase(pubvar.Sku);
            Log.d(TAG, "step10");

            if (purchase != null && verifyDeveloperPayload(purchase)) {
                try {
                    Log.d(TAG, "step11");
                    mhelper.launchPurchaseFlow((Activity) pubvar.context, pubvar.Sku, pubvar.RC_REQUEST, mPurchaseFinishedListener, pubvar.payload);
                    Log.d(TAG, "step12");
                } catch (IabHelper.IabAsyncInProgressException e) {
                    msg = e.toString();
                    Log.d(TAG, "step13");

                }

            }
            if (purchase == null) {
                msg = "user is subscribe";
            }
        }

    };
    private IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
            Log.d(TAG, "Purchase finished: " + result + ", purchase: " + purchase);


            if (result.isFailure()) {
                Log.d(TAG, "step14");

                return;
            }
            if (!verifyDeveloperPayload(purchase)) {
                Log.d(TAG, "step15");

                return;
            }

            Log.d(TAG, "Purchase successful.");
            msg = "purchase successful";
        }
    };

    private boolean verifyDeveloperPayload(Purchase p) {
        String payload = p.getDeveloperPayload();
        return true;
    }
}
