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
    public String msg2;
    public Context context;
    public String Sku;
    public String payload;
    public int RC_REQUEST;
    private Listener listener;
    private Listener2 listener2;
    private Listener3 listener3;
    private Listener4 listener4;

    private interface Listener {
        public void onSuccess(String msg);

        public void onFailure(String msg);
        public void onsuccesstoken(String msg2);
    }

    public interface Listener2 {
        public void onSuccess(String msg);

        public void onFailure(String msg);
        public void onsuccesstoken(String msg2);
    }
    public interface Listener3{
        public void onPakage(String pakagename,String Token);
    }
    public interface Listener4{
        public void onsuccesstoken(String pkj,String Tkn);
    }

    public void resListener(Listener listener) {
        this.listener = listener;
    }
    public void resListener2(Listener2 listener2) {
        this.listener2 = listener2;
    }
    public void resListener3(Listener3 listener3) {
        this.listener3 = listener3;
    }
    public void resListener4(Listener4 listener4){this.listener4=listener4;}
    public Charkh(publicvar p) {
        pubvar = p;
        msg = pubvar.Message[0];
        context = pubvar.context;
        Sku = pubvar.Sku;
        payload = pubvar.payload;
        RC_REQUEST = pubvar.RC_REQUEST;
    }

    private IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
            Log.d(TAG, "Purchase finished: " + result + ", purchase: " + purchase);
            if (result.isFailure()) {
                return;
            }
            if (!verifyDeveloperPayload(purchase)) {
                return;
            }

            Log.d(TAG, "Purchase successful.");
        }
    };
    private IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {

        @Override
        public void onQueryInventoryFinished(IabResult Result, Inventory inventory) {

            if (mhelper == null) {
                Log.d(TAG, "step6");
                return;
            }
            if (Result.isFailure()) {
                Log.d(TAG, "step7");
                try {
                    mhelper.launchPurchaseFlow((Activity) context, Sku, RC_REQUEST, mPurchaseFinishedListener, payload);
                    msg = "user never sub";
                    Log.d(TAG, "step8");
                } catch (IabHelper.IabAsyncInProgressException e) {
                    msg = e.toString();
                    Log.d(TAG, "step9");
                }
                msg2 = "Query Inventory :Finish";
                return;
            }
            Purchase purchase = inventory.getPurchase(pubvar.Sku);
            if (purchase != null) {
                try {

                    msg = "user is subscribe";
                    // barrier.await();


                } catch (Exception e) {
                    msg = e.toString();
                }
            }
            if (purchase == null) {

                try {
                    SkuDetails skuDetails = inventory.getSkuDetails(pubvar.Sku);
                    String j = skuDetails.getPrice();
                    msg = j;
                    Log.d(TAG, j);

                    Log.d(TAG, "step12");
                } catch (Exception e) {
                    msg = e.toString();
                    Log.d(TAG, "step13");

                }


            }
        }

    };
    private IabHelper.QueryInventoryFinishedListener mGotInventorysub = new IabHelper.QueryInventoryFinishedListener() {
        @Override
        public void onQueryInventoryFinished(IabResult Result, Inventory inventory) {
            if (mhelper == null) {
                listener.onSuccess("Setup Fail");
                return;
            }
            if (Result.isFailure()) {
                listener.onSuccess("User must be subscribe");

                return;
            }
            Purchase purchase = inventory.getPurchase(pubvar.Sku);

            if (purchase != null) {
                try {
                    listener.onSuccess("Subscribed :True");
                    return;

                } catch (Exception e) {
                    listener.onFailure(e.toString());
                    return;

                }

            }
            if (purchase == null) {
                listener.onSuccess("User must be subscribe");
                return;
            }
        }

    };

    private IabHelper.QueryInventoryFinishedListener mgotunsub = new IabHelper.QueryInventoryFinishedListener() {
        @Override
        public void onQueryInventoryFinished(IabResult Result, Inventory inventory) {
            if (mhelper == null) {
                return;
            }
            if (Result.isFailure()) {

                return;
            }
            Purchase purchase = inventory.getPurchase(pubvar.Sku);

            if (purchase != null) {
                try {
                    String pk=purchase.getPackageName();
                    String tk=purchase.getToken();
                    listener3.onPakage(pk,tk);

                    return;

                } catch (Exception e) {

                    return;

                }

            }
            if (purchase == null) {
                return;
            }
        }

    };

    public void mchar() {

        try {
            Log.d(TAG, "error111");

            mhelper.queryInventoryAsync(mGotInventoryListener);

        } catch (IabHelper.IabAsyncInProgressException e) {
            msg = e.toString();
            Log.d(TAG, msg);

        }


    }

    public void statuschar() {
        try {
            Log.d(TAG, "");


            mhelper.queryInventoryAsync(mGotInventorysub);

            Listener listener = new Listener() {
                @Override
                public void onSuccess(String msg) {
                    Log.i("TAGL", msg);
                    listener2.onSuccess(msg);
                }

                @Override
                public void onFailure(String msg) {
                    Log.i("TAGL", msg);
                    listener2.onFailure(msg);
                }

                @Override
                public void onsuccesstoken(String msg2) {

                }
            };

            resListener(listener);


        } catch (IabHelper.IabAsyncInProgressException e) {
            msg = e.toString();
            Log.d(TAG, msg);
        }


    }

    public void unsubchar() {
        try {
            mhelper.queryInventoryAsync(mgotunsub);
          Listener3 listener2=new Listener3() {
              @Override
              public void onPakage(String pakagename, String Token) {
                      listener4.onsuccesstoken(pakagename,Token);

                  Log.d("TAGL", "pkjtoken is :---->>>"+pakagename+"----"+Token);
              }
          };
            resListener3(listener2);

        } catch (IabHelper.IabAsyncInProgressException e) {
            msg = e.toString();
            Log.d(TAG, msg);
        }


    }

    public void setup(Context context, String key) {
        Log.d(TAG, "step1");
        mhelper = new IabHelper(context, key, new MarketIntentFactorySDK(true));
        Log.d(TAG, "step2");
        boolean f = mhelper.subscriptionsSupported();
        if (!f) {

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
                    msg2 = "onIabSetupFinished";
                    Log.d(TAG, "error3");

                    return;
                }
            }
        });
    }

    private boolean verifyDeveloperPayload(Purchase p) {
        String payload = p.getDeveloperPayload();
        return true;
    }


}
