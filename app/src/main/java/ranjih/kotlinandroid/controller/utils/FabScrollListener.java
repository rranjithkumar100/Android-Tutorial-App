package ranjih.kotlinandroid.controller.utils;

import android.app.Activity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import ranjih.kotlinandroid.R;

/**
 * Created by Ram on 6/26/2017.
 */

public class FabScrollListener extends RecyclerView.OnScrollListener {
    private FloatingActionButton mFAB;
    private static final String TAG = FabScrollListener.class.getSimpleName();

    public FabScrollListener(Activity activity) {
        try {
            if (null != activity)
                mFAB = (FloatingActionButton) activity.findViewById(R.id.fab);
        } catch (Exception e) {
            Log.e(TAG, "FabScrollListener: ", e);
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy > 0) {
            if (mFAB != null)
                mFAB.hide();
        } else if (dy < 0) {
            if (mFAB != null)
                mFAB.show();

        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (mFAB != null)
            mFAB.show();
        }
        super.onScrollStateChanged(recyclerView, newState);
    }
}
