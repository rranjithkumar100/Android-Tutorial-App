package ranjih.kotlinandroid.controller.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePref {
	private static final String TAG = SharePref.class.getSimpleName();

	private static SharePref mThis = null;
	private Context mContext = null;
	private SharedPreferences mPreference = null;

	private SharePref() {

	}

	public static void init(Context context) {
		if (mThis == null) {
			mThis = new SharePref();
			mThis.setData(context);
		} else {
			mThis.setData(context);
		}
	}

	public static SharePref getInstance() {
		return mThis;
	}

	private void setData(Context context) {
		mThis.mContext = context;
		mPreference = mContext.getSharedPreferences(TAG, Context.MODE_PRIVATE);
	}

	public void clearPreference(String key) {
		SharedPreferences.Editor editor = mPreference.edit();
		editor.remove(key).apply();
	}

	public void writeString(String tag, String data) {
		SharedPreferences.Editor editor = mPreference.edit();
		editor.putString(tag, data).apply();
	}

	public String readString(String tag) {
		return mPreference.getString(tag, "");
	}

	public void writeBoolean(String tag, boolean data) {
		SharedPreferences.Editor editor = mPreference.edit();
		editor.putBoolean(tag, data).apply();
	}

	public void writeLong(String tag, long data) {
		SharedPreferences.Editor editor = mPreference.edit();
		editor.putLong(tag, data).apply();
	}

	public long readLong(String tag) {
		return mPreference.getLong(tag, 0);
	}
	public boolean readBoolean(String tag) {
		return mPreference.getBoolean(tag, false);
	}

	public void clear(String tag) {
		mPreference.edit().remove(tag).apply();
	}


}
