package ranjih.kotlinandroid.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import ranjih.kotlinandroid.controller.utils.AppUtils;

/**
 * Created by Ranjithkumar on 01-06-2017.
 */

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
		super.onCreate(savedInstanceState, persistentState);

	}

    public static void initAdMob(Context context) {
        MobileAds.initialize(context);
    }

    @Override
	protected void onResume() {
		super.onResume();
		AppUtils.setFullScreen(this);
	}
}
