package ranjih.kotlinandroid.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.youtube.player.YouTubeBaseActivity;

import ranjih.kotlinandroid.R;
import ranjih.kotlinandroid.controller.utils.Keys;
import ranjih.kotlinandroid.view.widgets.CustomYouTubeView;

import static ranjih.kotlinandroid.controller.utils.AppUtils.loadIntertialAd;

/**
 * Created by Ram on 6/26/2017.
 */

public class YoutubeActivity extends YouTubeBaseActivity implements CustomYouTubeView.YouTubeIntializerListener {
    private static final String TAG = YoutubeActivity.class.getSimpleName();
    private LinearLayout rootView;

    @Override
    protected void onCreate(Bundle bundle) {

        super.onCreate(bundle);
        setContentView(R.layout.fragment_youtube_view);
        String youtubeId = null;
        if (getIntent() != null) {
            youtubeId = getIntent().getStringExtra(Keys.KEY_YOUTUBE_ID);
        }
        Log.d(TAG, "onCreate: yoututbe id " + youtubeId);
        CustomYouTubeView customYouTubeView = new CustomYouTubeView(this, youtubeId, this);
        rootView = (LinearLayout) findViewById(R.id.fyv_root_view);
        rootView.addView(customYouTubeView);
    }


    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        // disable view state saving to prevent saving states from youtube apk which cannot be restored.
        try {
            if (rootView != null) {
                ViewGroup viewGroup = rootView;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    viewGroup.getChildAt(i).setSaveFromParentEnabled(false);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "onSaveInstanceState: ", e);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //loadIntertialAd(this);

    }

    @Override
    public void youTubeInitialized() {
        Log.d(TAG, "youTubeInitialized: ");
    }

    @Override
    public void youTubeFalied() {
        Log.d(TAG, "youTubeFalied: ");
    }
}
