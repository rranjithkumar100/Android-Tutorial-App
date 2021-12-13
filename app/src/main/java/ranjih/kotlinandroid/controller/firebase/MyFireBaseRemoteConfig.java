package ranjih.kotlinandroid.controller.firebase;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import ranjih.kotlinandroid.BuildConfig;
import ranjih.kotlinandroid.R;
import ranjih.kotlinandroid.controller.utils.Keys;
import ranjih.kotlinandroid.controller.utils.SharePref;

import static ranjih.kotlinandroid.controller.utils.Keys.KEY_BANNER_AD_VISIBLITY;
import static ranjih.kotlinandroid.controller.utils.Keys.KEY_COLOR_ACCENT;
import static ranjih.kotlinandroid.controller.utils.Keys.KEY_COLOR_PRIMARY;
import static ranjih.kotlinandroid.controller.utils.Keys.KEY_COLOR_PRIMARY_DARK;
import static ranjih.kotlinandroid.controller.utils.Keys.KEY_COLOR_TEXT;
import static ranjih.kotlinandroid.controller.utils.Keys.KEY_COLOR_TITLE;
import static ranjih.kotlinandroid.controller.utils.Keys.KEY_FORCE_UPDATE;
import static ranjih.kotlinandroid.controller.utils.Keys.KEY_FORCE_UPDATE_VERSIONS_FROM;
import static ranjih.kotlinandroid.controller.utils.Keys.KEY_INTERSTIAL_AD_VISIBLITY;
import static ranjih.kotlinandroid.controller.utils.Keys.KEY_LIVE_VERSION_CODE;
import static ranjih.kotlinandroid.controller.utils.Keys.KEY_NEWS_AVAILABLE;

/**
 * Created by Ranjithkumar on 29-05-2017.
 */

public class MyFireBaseRemoteConfig {
	private static final MyFireBaseRemoteConfig ourInstance = new MyFireBaseRemoteConfig();
	private static final String TAG = MyFireBaseRemoteConfig.class.getSimpleName();

	private FirebaseRemoteConfig mFirebaseRemoteConfig;

	private MyFireBaseRemoteConfig() {
	}

	public static MyFireBaseRemoteConfig getInstance() {
		return ourInstance;
	}

	public void init(final Activity activity) {
		try {
			mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
			FirebaseRemoteConfigSettings remoteConfigSettings = new FirebaseRemoteConfigSettings.Builder()
					.setDeveloperModeEnabled(BuildConfig.DEBUG)
					.build();

			mFirebaseRemoteConfig.setConfigSettings(remoteConfigSettings);
			mFirebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults);
// cache expiration in seconds
			long cacheExpiration = 3600;
			if (mFirebaseRemoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled()) {
				cacheExpiration = 0;
			}

			final long finalCacheExpiration = cacheExpiration;
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {

					// Run mFirebaseRemoteConfig.fetch(timeout) here, and it works
					mFirebaseRemoteConfig.fetch(finalCacheExpiration)
							.addOnCompleteListener(activity, new OnCompleteListener<Void>() {
								@Override
								public void onComplete(Task<Void> task) {
									if (task.isSuccessful()) {
										// task successful. Activate the fetched data
										mFirebaseRemoteConfig.activateFetched();

										updateResults();


									} else {
										//task failed
							}
								}
							});
				}
			}, 0);

		} catch (Exception e) {
			Log.e(TAG, "init: ", e);
		}
	}

	private void updateResults() {
		String primaryColor = mFirebaseRemoteConfig.getString(KEY_COLOR_PRIMARY);
		String primaryDarkColor = mFirebaseRemoteConfig.getString(KEY_COLOR_PRIMARY_DARK);
		String color_accent = mFirebaseRemoteConfig.getString(KEY_COLOR_ACCENT);
		String titleColor = mFirebaseRemoteConfig.getString(KEY_COLOR_TITLE);
		String textColor = mFirebaseRemoteConfig.getString(KEY_COLOR_TEXT);

		boolean force_update = mFirebaseRemoteConfig.getBoolean(KEY_FORCE_UPDATE);
		long version_code = mFirebaseRemoteConfig.getLong(KEY_LIVE_VERSION_CODE);
		long force_update_versions_from = mFirebaseRemoteConfig.getLong(KEY_FORCE_UPDATE_VERSIONS_FROM);

		boolean interstial_ad_visiblity = mFirebaseRemoteConfig.getBoolean(KEY_INTERSTIAL_AD_VISIBLITY);
		boolean banner_ad_visiblity = mFirebaseRemoteConfig.getBoolean(KEY_BANNER_AD_VISIBLITY);

		boolean isNewsAvailable=mFirebaseRemoteConfig.getBoolean(KEY_NEWS_AVAILABLE);

		SharePref.getInstance().writeString(Keys.KEY_COLOR_PRIMARY, primaryColor);
		SharePref.getInstance().writeString(Keys.KEY_COLOR_PRIMARY_DARK, primaryDarkColor);
		SharePref.getInstance().writeString(Keys.KEY_COLOR_ACCENT, color_accent);
		SharePref.getInstance().writeString(Keys.KEY_COLOR_TITLE, titleColor);
		SharePref.getInstance().writeString(Keys.KEY_COLOR_TEXT, textColor);
		SharePref.getInstance().writeBoolean(Keys.KEY_BANNER_AD_VISIBLITY, banner_ad_visiblity);
		SharePref.getInstance().writeBoolean(Keys.KEY_INTERSTIAL_AD_VISIBLITY, interstial_ad_visiblity);
		SharePref.getInstance().writeBoolean(KEY_FORCE_UPDATE, force_update);
		SharePref.getInstance().writeBoolean(KEY_NEWS_AVAILABLE,isNewsAvailable);
		SharePref.getInstance().writeLong(KEY_LIVE_VERSION_CODE, version_code);
		SharePref.getInstance().writeLong(KEY_FORCE_UPDATE_VERSIONS_FROM, force_update_versions_from);

		Log.d(TAG, "init: primaryColor " + primaryColor);
	}


}
