package ranjih.kotlinandroid.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

import ranjih.kotlinandroid.R;
import ranjih.kotlinandroid.controller.firestore.FireStoreListener;
import ranjih.kotlinandroid.controller.firestore.FireStoreUtil;
import ranjih.kotlinandroid.controller.utils.AppUtils;
import ranjih.kotlinandroid.controller.utils.FabScrollListener;
import ranjih.kotlinandroid.controller.utils.SharePref;
import ranjih.kotlinandroid.view.adapter.NewsAdapter;

import static ranjih.kotlinandroid.controller.utils.Keys.KEY_NEWS_AVAILABLE;

/**
 * Created by Ram on 6/25/2017.
 */

public class FragmentNews extends BaseFragment implements FireStoreListener {
    private RecyclerView mRecyclerView;
    private TextView tvNoNews;
    private static final String TAG = "FragmentNews";
    private ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_news);
        tvNoNews=view.findViewById(R.id.tv_news_not_available);
         progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        AppUtils.setProgressBarColor(progressBar);

        if(SharePref.getInstance().readBoolean(KEY_NEWS_AVAILABLE)) {
            progressBar.setVisibility(View.VISIBLE);
            Log.d(TAG, "onViewCreated: ");
            FireStoreUtil.getInstance().init();
            FireStoreUtil.getInstance().getNews(1, this);
        }
        else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(SharePref.getInstance().readBoolean(KEY_NEWS_AVAILABLE)) {
                        progressBar.setVisibility(View.VISIBLE);
                        Log.d(TAG, "onViewCreated: news available");
                        FireStoreUtil.getInstance().init();
                        FireStoreUtil.getInstance().getNews(1, FragmentNews.this);
                    }
                    else {
                        Log.d(TAG, "onViewCreated: news not available");
                        progressBar.setVisibility(View.GONE);
                        tvNoNews.setVisibility(View.VISIBLE);
                    }

                }
            },7000);

        }
    }

    private void setRecyclerAdapter( Task<QuerySnapshot> task) {

        NewsAdapter youtubeRecyclerAdapter = new NewsAdapter(getActivity(), task);
        mRecyclerView.setAdapter(youtubeRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addOnScrollListener(new FabScrollListener(getActivity()));

    }


    @Override
    public void success(int requestCode, Task<QuerySnapshot> task) {
        progressBar.setVisibility(View.GONE);
        if(task!=null && task.getResult().size()>0) {
            setRecyclerAdapter(task);
        }
        else {
            tvNoNews.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void failure(int requestCode, Exception exception) {
        tvNoNews.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }
}
