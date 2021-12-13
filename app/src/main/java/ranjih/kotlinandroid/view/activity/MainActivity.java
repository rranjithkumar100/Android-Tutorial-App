package ranjih.kotlinandroid.view.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.fernandodev.easyratingdialog.library.EasyRatingDialog;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import ranjih.kotlinandroid.R;
import ranjih.kotlinandroid.controller.firebase.MyFireBaseRemoteConfig;
import ranjih.kotlinandroid.controller.utils.AppUtils;
import ranjih.kotlinandroid.controller.utils.Keys;
import ranjih.kotlinandroid.controller.utils.SharePref;
import ranjih.kotlinandroid.controller.utils.VersionController;
import ranjih.kotlinandroid.view.fragments.FragmentAndroidToKotlin;
import ranjih.kotlinandroid.view.fragments.FragmentAndroidVideos;
import ranjih.kotlinandroid.view.fragments.FragmentJavaToKotlin;
import ranjih.kotlinandroid.view.fragments.FragmentKotlinBasics;
import ranjih.kotlinandroid.view.fragments.FragmentKotlinInterviewQtns;
import ranjih.kotlinandroid.view.fragments.FragmentKotlinVideos;
import ranjih.kotlinandroid.view.fragments.FragmentNews;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    public Toolbar mToolbar;
    private AdView mAdView;
private EasyRatingDialog easyRatingDialog;
    private Snackbar mSnackBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        easyRatingDialog = new EasyRatingDialog(this);

        View rootView = findViewById(R.id.root_view);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mAdView = (AdView) findViewById(R.id.adView);
        mSnackBar = Snackbar.make(rootView, getString(R.string.exit_warning), Snackbar.LENGTH_LONG);


        if (SharePref.getInstance().readBoolean(Keys.KEY_BANNER_AD_VISIBLITY)) {
            AdRequest.Builder adBuilder = new AdRequest.Builder();
/*            adBuilder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    // Check the LogCat to get your test device ID
                    .addTestDevice("4C9AED4DAD2042C84C4AE85B421D9648");*/
            AdRequest adRequest = adBuilder
                    .build();

            mAdView.loadAd(adRequest);

            mAdView.setAdListener(new AdListener(){
                @Override
                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                    Log.d(TAG, "onAdFailedToLoad: error code "+i);
                }

                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    Log.d(TAG, "onAdLoaded: ");

                }

            });
        }
        long liveVersionCode = SharePref.getInstance().readLong(Keys.KEY_LIVE_VERSION_CODE);
        if (liveVersionCode > 0) {
            boolean isForceUpdate = SharePref.getInstance().readBoolean(Keys.KEY_FORCE_UPDATE);
            VersionController versionController = new VersionController(MainActivity.this);
            versionController.checkVersions((int) liveVersionCode, isForceUpdate);
        }

        setSupportActionBar(mToolbar);
        mToolbar.setFitsSystemWindows(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        navigationView.getMenu().getItem(0).setChecked(true);

        if(getIntent()!=null && getIntent().getBooleanExtra("isNews",false)){
            switchFragment(FragmentNews.class);
        }
        else {
            switchFragment(FragmentKotlinBasics.class);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        easyRatingDialog.onStart();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        MyFireBaseRemoteConfig.getInstance().init(MainActivity.this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    /*	else if (mFragment instanceof FragmentWebView && ((FragmentWebView) mFragment).mWebView.canGoBack()) {
            ((FragmentWebView) mFragment).mWebView.goBack();
		}*/
        else if (getFragmentManager().getBackStackEntryCount() > 0) {
            Log.d(TAG, "onBackPressed: 2");
            getFragmentManager().popBackStack();
        } else {
            backPressAlert();
        }
    }

    private void clearBackStack() {
        FragmentManager manager = getFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppUtils.setFullScreen(MainActivity.this);
        easyRatingDialog.showIfNeeded();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_kotlin_basics:
                setTitle(item.getTitle());
                clearBackStack();
                switchFragment(FragmentKotlinBasics.class);
                break;

            case R.id.nav_news:
                MyFireBaseRemoteConfig.getInstance().init(MainActivity.this);
                setTitle(item.getTitle());
                clearBackStack();
                switchFragment(FragmentNews.class);
                break;

            case R.id.nav_interview_qtns:
                setTitle(item.getTitle());
                clearBackStack();
                switchFragment(FragmentKotlinInterviewQtns.class);
                break;

            case R.id.nav_video_tutorial:
                if (AppUtils.isNetworkAvailable(getApplicationContext())) {
                    setTitle(item.getTitle());
                    clearBackStack();
                    switchFragment(FragmentKotlinVideos.class);
                } else {
                    Toast.makeText(getApplicationContext(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
                }
                break;


            case R.id.nav_android_video:
                if (AppUtils.isNetworkAvailable(getApplicationContext())) {
                    setTitle(item.getTitle());
                    clearBackStack();
                    switchFragment(FragmentAndroidVideos.class);
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.internet_warning), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.nav_java_to_kotlin:
                setTitle(item.getTitle());
                clearBackStack();
                switchFragment(FragmentJavaToKotlin.class);
                break;
            case R.id.nav_android_to_kotlin:
                setTitle(item.getTitle());
                clearBackStack();
                switchFragment(FragmentAndroidToKotlin.class);
                break;

            case R.id.nav_share:
                String shareBody = "https://play.google.com/store/apps/details?id=" + getPackageName();
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_subject));
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
                break;

/*
            case R.id.nav_feedback:
                try {
                    String[] emailAddress = {getResources().getString(R.string.care_mail)};
                    composeEmail(MainActivity.this, emailAddress, getResources().getString(R.string.mail_subject));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
*/

            case R.id.nav_privacy:
                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://privacypolicies.com/privacy/view/01cf1c57c56ac71bc969f208781bdadb"));
                startActivity(i);
                break;

            case R.id.nav_rating:
                launchRating();
                break;
/*            case R.id.nav_about:
                setTitle(item.getTitle());
                clearBackStack();
                switchFragment(FragmentAbout.class);
                break;*/
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void launchRating() {
        Uri uri = Uri.parse("market://details?id=" + getPackageName());
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Unable to find market app", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void composeEmail(Activity activity, String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        try {
            if (intent.resolveActivity(activity.getPackageManager()) != null) {
                activity.startActivity(intent);
            }
        } catch (Exception e) {
            Toast.makeText(activity, activity.getResources().getString(R.string.warning_mail_app_not_found) + activity.getResources().getString(R.string.care_mail), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void switchFragment(Class fragmentClass) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            Log.e(TAG, "switchFragment ERROR", e);
        }


        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
    }

    private void backPressAlert() {

        if (mSnackBar.getView().isShown()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                finishAffinity();
            } else {
                finish();
            }
        }
        else
            mSnackBar.show();
    }

    public void setTitle(String title) {
        mToolbar.setTitle(title);

    }

}
