package ranjih.kotlinandroid.view.fragments;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Arrays;
import java.util.List;

import ranjih.kotlinandroid.R;
import ranjih.kotlinandroid.controller.utils.HtmlFileKeys;
import ranjih.kotlinandroid.controller.utils.Keys;
import ranjih.kotlinandroid.controller.utils.OnSwipeTouchListener;
import ranjih.kotlinandroid.model.AndroidProgramsTemplate;
import ranjih.kotlinandroid.model.ComparisionTemplate;
import ranjih.kotlinandroid.view.activity.MainActivity;
import ranjih.kotlinandroid.view.widgets.NestedWebView;


/**
 * Created by Ranjithkumar on 01-06-2017.
 */

public class FragmentWebView extends BaseFragment {
    private static final String TAG = FragmentWebView.class.getSimpleName();
    public NestedWebView mWebView;
    private Context mContext;
    private String mURL;
    private ProgressDialog mProgressDialog;
    private boolean isCodeComparisionChecked = false;
    private int mCurrentIndex = 0;

    private boolean mIsKotlinBasics = false, mIsJavaVsKotlin = false, mIsAndroidTutorial = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_web_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            mCurrentIndex = getArguments().getInt(Keys.KEY_POSITION);
            mIsKotlinBasics = getArguments().getBoolean(Keys.KEY_IS_KOTLIN_BASICS);
            mIsJavaVsKotlin = getArguments().getSerializable(Keys.KEY_COMPARISION_TEMPLATE) != null;
            mIsAndroidTutorial = getArguments().getSerializable(Keys.KEY_ANDROID_PGMS_TEMPLATE) != null;
        } catch (Exception e) {
            Log.e(TAG, "onViewCreated: ", e);
        }
        mWebView = (NestedWebView) view.findViewById(R.id.webview);
        mWebView.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
                                        @Override
                                        public void onSwipeLeft() {
                                            super.onSwipeLeft();
                                            if (mIsKotlinBasics)
                                                loadNextKotlinBasics(1);
                                            else if (mIsJavaVsKotlin)
                                                loadNextJavaVsKotlin(1);
                                            else if (mIsAndroidTutorial)
                                                loadNextAndroidTutorial(1);


                                        }

                                        @Override
                                        public void onSwipeRight() {
                                            super.onSwipeRight();
                                            if (mIsKotlinBasics)
                                                loadNextKotlinBasics(-1);
                                            else if (mIsJavaVsKotlin)
                                                loadNextJavaVsKotlin(-1);
                                            else if (mIsAndroidTutorial)
                                                loadNextAndroidTutorial(-1);

                                        }

                                        @Override
                                        public boolean onTouch(View v, MotionEvent event) {
                                            return super.onTouch(v, event);
                                        }
                                    }
        );


        try {
            final FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
            mWebView.setOnScrollChangedCallback(new NestedWebView.OnScrollChangedCallback() {
                @Override
                public void onScrollChange(WebView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (scrollY > oldScrollY && scrollY > 0) {
                        fab.hide();
                    }
                    if (scrollY < oldScrollY) {
                        fab.show();
                    }
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "onViewCreated: ", e);
        }
        mContext = getActivity();
        webViewSettings();
        mURL = getArguments().getString(Keys.KEY_URL);

        if (mURL != null) {
            mWebView.loadUrl(mURL);
        }


    }

    private void loadNextKotlinBasics(int incrementOrDecrement) {
        int tempIndex = mCurrentIndex + incrementOrDecrement;
        if (!(tempIndex < 0) && !(tempIndex > HtmlFileKeys.getInstance().getmKotlinBasicsUrls().size() - 1)) {
            mCurrentIndex = mCurrentIndex + incrementOrDecrement;
            mWebView.loadUrl(HtmlFileKeys.getInstance().getmKotlinBasicsUrls().get(mCurrentIndex));
        }
    }

    private void checkCodeComparisionAvilalable() {
        if (getArguments().getSerializable(Keys.KEY_COMPARISION_TEMPLATE) != null) {
            try {
                ComparisionTemplate mComparisionTemplate = (ComparisionTemplate) getArguments().getSerializable(Keys.KEY_COMPARISION_TEMPLATE);
                if (mComparisionTemplate != null) {
                    Log.d(TAG, "onViewCreated:comparisionTemplate " + mComparisionTemplate.getCode1());
                    setCodeComparision(mComparisionTemplate.getMainTitle(), mComparisionTemplate.getTitle1(), mComparisionTemplate.getTitle2(), mComparisionTemplate.getCode1(), mComparisionTemplate.getCode2());

                } else {
                    Log.d(TAG, "onViewCreated:comparisionTemplate null ");
                }
            } catch (Exception e) {
                Log.e(TAG, "onViewCreated: comparisionTemplate ", e);
            }
        } else {
            Log.d(TAG, "onViewCreated: getaArguments comparisionTemplate null ");
        }
    }

    private void setCodeComparision(String mainTitle, String title1, String title2, String code1, String code2) {
        StringBuilder stringBuilder =
                new StringBuilder("javascript:testFunction('" + mainTitle + "','" + title1 + "','" + title2 + "','" + code1 + "','" + code2 + "')");
        mWebView.loadUrl(stringBuilder.toString());

    }

    private void setAndroidCode(String title1, String code1) {
        StringBuilder stringBuilder =
                new StringBuilder("javascript:testFunction('" + title1 + "','" + code1 + "')");
        mWebView.loadUrl(stringBuilder.toString());

    }

    private void loadNextJavaVsKotlin(int incrementOrDecrement) {
        int tempIndex = mCurrentIndex + incrementOrDecrement;
        if (!(tempIndex < 0) && !(tempIndex > HtmlFileKeys.getInstance().getmJavaToKotlinKeys().size() - 1)) {
            mCurrentIndex = mCurrentIndex + incrementOrDecrement;

            String key = HtmlFileKeys.getInstance().getmJavaToKotlinKeys().get(mCurrentIndex);
            int resId = getResources().getIdentifier(key, "array", getActivity().getPackageName());
            List<String> strArrays = Arrays.asList(getResources().getStringArray(resId));
            if (strArrays.size() >= 3) {
                setCodeComparision(strArrays.get(0), "Java", "Kotlin", strArrays.get(1), strArrays.get(2));
            }
        }

    }

    private void loadNextAndroidTutorial(int incrementOrDecrement) {
        int tempIndex = mCurrentIndex + incrementOrDecrement;
        if (!(tempIndex < 0) && !(tempIndex > HtmlFileKeys.getInstance().getmAndroidTutorialKeys().size() - 1)) {
            mCurrentIndex = mCurrentIndex + incrementOrDecrement;

            String key = HtmlFileKeys.getInstance().getmAndroidTutorialKeys().get(mCurrentIndex);
            String title = HtmlFileKeys.getInstance().getmAndroidTutorialTitles().get(mCurrentIndex);
            int resId = getResources().getIdentifier(key, "string", getActivity().getPackageName());
            String code = getString(resId);
            if (code != null && code.length() > 0) {
                setAndroidCode(title, code);
            }
        }
    }

    private void webViewSettings() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
            // chromium, enable hardware acceleration
            mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            // older android version, disable hardware acceleration
            mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }


        String strCrome = "Mozilla/5.0 (Linux; U; Android-4.0.3; en-us; Xoom Build/IML77) AppleWebKit/535.7 (KHTML, like Gecko) CrMo/16.0.912.75 Safari/535.7";
        mWebView.getSettings().setUserAgentString(strCrome);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);

        mWebView.setWebViewClient(new WebViewClient() {

                                      @Override
                                      public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                          view.loadUrl(url);
                                          return true;
                                      }

                                      @TargetApi(Build.VERSION_CODES.N)
                                      @Override
                                      public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                                          final Uri uri = request.getUrl();
                                          Log.d(TAG, "shouldOverrideUrlLoading: " + uri.getHost());
                                          view.loadUrl(uri.getHost());
                                          return handleUri(uri);
                                      }

                                      @Override
                                      public void onPageStarted(WebView view, String url, Bitmap favicon) {
                                          if (mProgressDialog != null && mProgressDialog.isShowing()) {
                                              mProgressDialog.dismiss();
                                          }

                                          mProgressDialog = ProgressDialog.show(mContext, view.getTitle(), "Loading...");
                                      }

                                      @Override
                                      public void onPageFinished(WebView view, String url) {
                                          super.onPageFinished(view, url);
                                          try {
                                              try {
                                                  if (!getActivity().isFinishing() && mProgressDialog != null && mProgressDialog.isShowing()) {
                                                      mProgressDialog.dismiss();
                                                  }
                                              } catch (Exception e) {
                                                  Log.e(TAG, "onPageFinished: ", e);
                                              }
                                              if (!isCodeComparisionChecked) {
                                                  isCodeComparisionChecked = true;
                                                  checkCodeComparisionAvilalable();
                                                  checkAndroidCodeAvailable();
                                              }
                                              if (getActivity() != null && view.getTitle() != null && !view.getTitle().isEmpty()) {
                                                  ((MainActivity) getActivity()).setTitle(view.getTitle());
                                              }
                                          } catch (Exception e) {
                                              Log.e(TAG, "onPageFinished: ", e);
                                          }
                                      }
                                  }
        );
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setDisplayZoomControls(false);
    }

    private void checkAndroidCodeAvailable() {
        if (getArguments().getSerializable(Keys.KEY_ANDROID_PGMS_TEMPLATE) != null) {
            try {
                AndroidProgramsTemplate androidProgramsTemplate = (AndroidProgramsTemplate) getArguments().getSerializable(Keys.KEY_ANDROID_PGMS_TEMPLATE);
                if (androidProgramsTemplate != null) {

                    setAndroidCode(androidProgramsTemplate.getTitle(), androidProgramsTemplate.getCode());

                } else {
                    Log.d(TAG, "onViewCreated:comparisionTemplate null ");
                }
            } catch (Exception e) {
                Log.e(TAG, "onViewCreated: comparisionTemplate ", e);
            }
        } else {
            Log.d(TAG, "onViewCreated: getaArguments comparisionTemplate null ");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private boolean handleUri(final Uri uri) {
        Log.i(TAG, "Uri =" + uri);
        final String host = uri.getHost();
        final String scheme = uri.getScheme();
        // Based on some condition you need to determine if you are going to load the url
        // in your web view itself or in a browser.
        // You can use `host` or `scheme` or any part of the `uri` to decide.
        return false;
    }


}
