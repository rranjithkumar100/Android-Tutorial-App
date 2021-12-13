package ranjih.kotlinandroid.view.activity;

import android.animation.Animator;
import android.app.Activity;
import android.os.Handler;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

import ranjih.kotlinandroid.R;
import ranjih.kotlinandroid.controller.utils.AppUtils;
import ranjih.kotlinandroid.controller.utils.Constants;

/**
 * Created by varsovski on 01-Oct-15.
 */

//extends AwesomeSplash!
public class SplashActivity extends AwesomeSplash {

	//DO NOT OVERRIDE onCreate()!
	//if you need to start some services do it in initSplash()!
	private Handler mHandler;

	@Override
	public void initSplash(ConfigSplash configSplash) {
		AppUtils.setFullScreen(this);
		getAndSetSplashValues(configSplash);
	}

    @Override
    protected void onStart() {
        super.onStart();
        BaseActivity.initAdMob(this);
    }

    @Override
    public void animationsFinished() {

    }


    private void getAndSetSplashValues(ConfigSplash configSplash) {
	        /* you don't have to override every property */

		//Customize Circular Reveal
		configSplash.setBackgroundColor(R.color.colorPrimary); //any color you want form colors.xml
		configSplash.setAnimCircularRevealDuration(1000); //int ms
		configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
		configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

		//Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

		//Customize Logo
//        configSplash.setLogoSplash(R.mipmap.ic_launcher); //or any other drawable
//        configSplash.setAnimLogoSplashDuration(2000); //int ms
//        configSplash.setAnimLogoSplashTechnique(Techniques.Bounce); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)


		//Customize Path
		configSplash.setPathSplash(Constants.DROID_LOGO); //set path String
		configSplash.setOriginalHeight(400); //in relation to your svg (path) resource
		configSplash.setOriginalWidth(400); //in relation to your svg (path) resource
		configSplash.setAnimPathStrokeDrawingDuration(600);
		configSplash.setPathSplashStrokeSize(3); //I advise value be <5
		configSplash.setPathSplashStrokeColor(R.color.icons); //any color you want form colors.xml
		configSplash.setAnimPathFillingDuration(400);
		configSplash.setPathSplashFillColor(R.color.colorPrimaryDark); //path object filling color


		//Customize Title
        configSplash.setTitleSplash(getString(R.string.splash_txt));
        configSplash.setTitleTextColor(R.color.Wheat);
		configSplash.setTitleTextSize(30f); //float value
		configSplash.setAnimTitleDuration(400);
		//configSplash.setAnimTitleTechnique(Techniques.ZoomInUp);
		configSplash.setAnimLogoSplashTechnique(com.daimajia.androidanimations.library.Techniques.ZoomInUp);
//        configSplash.setTitleFont("fonts/myfont.ttf"); //provide string to your font located in assets/fonts/
	}


    @Override
    public void onAnimationEnd(Animator animation) {
        super.onAnimationEnd(animation);
        final Activity a = this;
        if (mHandler == null) {
            mHandler = new Handler();
        }
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                AppUtils.startActivity(SplashActivity.this, MainActivity.class);
            }
        }, Constants.SPLASH_DELAY);
    }

    @Override
	protected void onStop() {
		super.onStop();
		if (mHandler != null)
			mHandler.removeCallbacksAndMessages(null);
	}

	@Override
	protected void onResume() {
		super.onResume();
		super.onRestart();
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (mHandler != null) {
			mHandler.removeCallbacksAndMessages(null);
		}

	}
}
