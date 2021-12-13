package ranjih.kotlinandroid.view.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebView;

/**
 * Created by Ram on 6/26/2017.
 */

public class NestedWebView extends WebView {

    private static final String TAG = NestedWebView.class.getSimpleName();
    private OnScrollChangedCallback mOnScrollChangedCallback;
    public NestedWebView(Context context) {
        super(context);
    }

    public NestedWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NestedWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        try {
            if (mOnScrollChangedCallback != null) {
                mOnScrollChangedCallback.onScrollChange(this, l, t, oldl, oldt);
            }
        } catch (Exception e) {
            Log.e(TAG, "onScrollChanged: ", e);
        }

    }

    public OnScrollChangedCallback getOnScrollChangedCallback() {
        return mOnScrollChangedCallback;
    }

    public void setOnScrollChangedCallback(final OnScrollChangedCallback mOnScrollChangedCallback) {
        this.mOnScrollChangedCallback = mOnScrollChangedCallback;
    }

    public interface OnScrollChangedCallback {
        /**
         * Called when the scroll position of a view changes.
         *
         * @param v          The view whose scroll position has changed.
         * @param scrollX    Current horizontal scroll origin.
         * @param scrollY    Current vertical scroll origin.
         * @param oldScrollX Previous horizontal scroll origin.
         * @param oldScrollY Previous vertical scroll origin.
         */
        void onScrollChange(WebView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY);
    }
}
