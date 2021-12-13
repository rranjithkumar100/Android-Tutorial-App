package ranjih.kotlinandroid;

import android.app.Application;
import android.content.Context;


import com.google.android.gms.ads.MobileAds;
import com.google.firebase.messaging.FirebaseMessaging;

import ranjih.kotlinandroid.controller.utils.SharePref;

/**
 * Created by Ranjithkumar on 02-06-2017.
 */

public class App extends Application {
	private static final String TAG = App.class.getSimpleName();

private static Context APP_CONTEXT;
	@Override
	public void onCreate() {
		super.onCreate();
		APP_CONTEXT=this;
        MobileAds.initialize(this);
        FirebaseMessaging.getInstance().subscribeToTopic("news");
		SharePref.init(this);

	}

	public static Context getContext(){
		return APP_CONTEXT;
	}
}
