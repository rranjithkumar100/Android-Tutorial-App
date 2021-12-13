package ranjih.kotlinandroid.view.widgets;

/**
 * Created by Ram on 6/26/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import ranjih.kotlinandroid.R;

/**
 * Created by Ranjithkumar on 18-10-2016.
 */

public class CustomYouTubeView extends LinearLayout implements YouTubePlayer.OnInitializedListener {

    public YouTubePlayerView mYouTubePlayerView;
    private String mYouTubeVideoId;
    private String TAG = CustomYouTubeView.class.getSimpleName();
    private YouTubeIntializerListener youTubeIntializerListener;

    public CustomYouTubeView(Context context) {
        super(context);
    }


    public CustomYouTubeView(Activity context, String youTubeVideoId, YouTubeIntializerListener youTubeIntializerListener) {
        super(context);

        Log.d(TAG, "CustomYouTubeView :: ");
        this.youTubeIntializerListener = youTubeIntializerListener;

        mYouTubeVideoId = youTubeVideoId;
        //String apiKey = "AIzaSyCLLeT5eV5jCHZIjNME4MG6q_GEftd8crQ";
        String apiKey = context.getString(R.string.youtube_api_key);

        mYouTubePlayerView = new YouTubePlayerView(context);
        mYouTubePlayerView.setTag(mYouTubeVideoId);
        mYouTubePlayerView.initialize(apiKey, this);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        //int margin = (int) ScreenUtils.dip2pixel(10, context);
        params.leftMargin = 10;
        params.rightMargin = 10;
        params.topMargin = 10;
        params.bottomMargin = 10;
        addView(mYouTubePlayerView, params);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {
        Log.d(TAG, "onInitializationFailure()");
        youTubeIntializerListener.youTubeFalied();
    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer player, final boolean wasRestored) {

        Log.d(TAG, "onInitializationSuccess()" + mYouTubeVideoId);
        if (!wasRestored) {
            player.cueVideo(mYouTubeVideoId);
            youTubeIntializerListener.youTubeInitialized();

        }


    }

    public interface YouTubeIntializerListener {
        void youTubeInitialized();

        void youTubeFalied();
    }
}