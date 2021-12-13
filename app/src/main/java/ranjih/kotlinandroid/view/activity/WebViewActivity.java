package ranjih.kotlinandroid.view.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import ranjih.kotlinandroid.R;
import ranjih.kotlinandroid.controller.utils.AppUtils;
import ranjih.kotlinandroid.controller.utils.Keys;
import ranjih.kotlinandroid.controller.utils.SharePref;


/**
 * Created by Ranjithkumar on 16/10/17.
 */
public class WebViewActivity extends BaseActivity {
    private static final String TAG = WebViewActivity.class.getSimpleName();
    private Toolbar toolbar;
private String newsTitle,url;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //AppUtils.setToolBarColor(this, toolbar);
        AppUtils.removeToolbarPadding(toolbar);
        setSupportActionBar(toolbar);
        AppUtils.setFullScreen(this);
        WebView webView = (WebView) findViewById(R.id.webview_privacy);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        AppUtils.setProgressBarColor(progressBar);
        progressBar.setVisibility(View.VISIBLE);


       // webView.getSettings().setSupportZoom(true);
        webView.setInitialScale(1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        webView.getSettings().setBuiltInZoomControls(true);
         url = getIntent().getStringExtra(Keys.KEY_URL);
        newsTitle= getIntent().getStringExtra(Keys.NEWS_TITLE);
         String title = "News";
        Log.d(TAG, "onCreate: " + url);
        if (url != null)
            webView.loadUrl(url);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        webView.setWebViewClient(new WebViewClient() {

                                     @Override
                                     public void onPageFinished(WebView view, String url) {
                                         super.onPageFinished(view, url);
                                         if (!isFinishing()) {
                                             if (progressBar != null)
                                                 progressBar.setVisibility(View.GONE);
                                         }
                                     }
                                 }
        );

        if (SharePref.getInstance().readBoolean(Keys.KEY_BANNER_AD_VISIBLITY)) {
            AdRequest.Builder adBuilder = new AdRequest.Builder();
/*            adBuilder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    // Check the LogCat to get your test device ID
                    .addTestDevice("4C9AED4DAD2042C84C4AE85B421D9648");*/
            AdRequest adRequest = adBuilder
                    .build();
            AdView mAdView=findViewById(R.id.adView);
            mAdView.loadAd(adRequest);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else if (item.getItemId() == R.id.action_share) {
            AppUtils.shareNews(newsTitle,url,WebViewActivity.this);
            return true;
        } else
            return super.onOptionsItemSelected(item);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.share, menu);
        return true;
    }
}
