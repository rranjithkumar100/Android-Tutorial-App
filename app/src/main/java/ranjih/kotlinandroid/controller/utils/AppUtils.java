package ranjih.kotlinandroid.controller.utils;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.Display;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ranjih.kotlinandroid.R;

import static android.content.Context.INPUT_METHOD_SERVICE;


public class AppUtils {

    private static final String TAG = AppUtils.class.getSimpleName();


    public static void setFullScreen(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void startActivity(Activity activity, Class NextActivity) {
        Intent homeIntent = new Intent(activity, NextActivity);
        activity.startActivity(homeIntent);
        activity.finish();
    }

    public static void loadIntertialAd(Context context) {
        try {
            final InterstitialAd mInterstitialAd = new InterstitialAd(context);
            mInterstitialAd.setAdUnitId(context.getResources().getString(R.string.admob_intertial_id));
            AdRequest adRequestInter = new AdRequest.Builder().build();
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    mInterstitialAd.show();
                }
            });
            mInterstitialAd.loadAd(adRequestInter);
        } catch (Exception e) {
            Log.e(TAG, "loadIntertialAd: ", e);
        }
    }

    public static String getYoutubeVideoId(String youtubeUrl) {
        String video_id = "";
        try {
            if (youtubeUrl != null && youtubeUrl.trim().length() > 0 && youtubeUrl.startsWith("http")) {

                String expression = "^.*((youtu.be" + "\\/)" + "|(v\\/)|(\\/u\\/w\\/)|(embed\\/)|(watch\\?))\\??v?=?([^#\\&\\?]*).*"; // var regExp = /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#\&\?]*).*/;
                CharSequence input = youtubeUrl;
                Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(input);
                if (matcher.matches()) {
                    String groupIndex1 = matcher.group(7);
                    if (groupIndex1 != null && groupIndex1.length() == 11)
                        video_id = groupIndex1;
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "getYoutubeVideoId: ", e);
        }
        return video_id;
    }

    public static void startActivity(Activity activity, Class NextActivity, boolean isFinish) {
        Intent homeIntent = new Intent(activity, NextActivity);
        activity.startActivity(homeIntent);
        if (isFinish)
            activity.finish();
    }

    public static void switchFragment(int containerId, Activity activity, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = activity.getFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerId, fragment, tag);
        fragmentTransaction.commit();
        if (tag != null)
            activity.setTitle(tag);
    }


    public static void addFragment(int containerId, Activity activity, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = activity.getFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerId, fragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    public static void hideSoftKeyboard(Activity activity) {
        if (activity.getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static void setRecyclerViewAnimation(RecyclerView recyclerView) {
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);
    }

    public static void setEditTextMaxLength(EditText editText, int length) {
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(length);
        if (editText != null)
            editText.setFilters(FilterArray);
    }


    public static String getCommaSepartedString(Iterable tokens) {
        if (tokens != null) {
            return TextUtils.join(",", tokens);
        }
        return "";
    }


    public static boolean isValidEmail(CharSequence target) {
        return target != null && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static boolean isValidPhone(CharSequence target) {
        return target != null && Patterns.PHONE.matcher(target).matches() && target.length() == 10;
    }

    /*Get the screen width of the device*/
    public static int getScreenWidth(Activity context) {
        int width;
        Display display = context.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        return width;
    }


    public static String getTodayDate() {
        return new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
    }

    public static String getCurrentTime() {
        return new SimpleDateFormat("h:mm a", Locale.getDefault()).format(new Date());
    }


    public static int generateRandomNumber(int min, int max) {
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }

    public static boolean isTablet(Activity activity) {
        if (activity != null) {
            DisplayMetrics metrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            float yInches = (float) metrics.heightPixels / metrics.ydpi;
            float xInches = (float) metrics.widthPixels / metrics.xdpi;
            double diagonalInches = Math.sqrt((double) (xInches * xInches + yInches * yInches));
            return diagonalInches >= 6.5D;
        }
        return false;
    }

    public static void disableSoftInputFromAppearing(EditText editText) {
        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editText.setTextIsSelectable(true);
    }

    public static void removeToolbarPadding(Toolbar toolbar) {
        toolbar.setContentInsetsAbsolute(0, 0);
    }

    public static void setProgressBarColor(ProgressBar progressBar) {
        int colorCode;
        try {
            colorCode = Color.parseColor("#00BBD3");
        } catch (Exception e) {
            colorCode = Color.BLACK;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            progressBar.setIndeterminateTintList(ColorStateList.valueOf(colorCode));
        }
    }


    public static void shareNews(String title, String url, Activity activity) {
        try {
            String shareBody = "https://play.google.com/store/apps/details?id=" + activity.getPackageName();
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "" + title);
            String finalBody = title+"\n" + url + "\n\nDownload app for more news \n" + shareBody;
            sharingIntent.putExtra(Intent.EXTRA_TEXT, finalBody);
            activity.startActivity(Intent.createChooser(sharingIntent, "Share using"));
        } catch (Exception e) {
            Log.e(TAG, "shareNews: ", e);
        }

    }

}
