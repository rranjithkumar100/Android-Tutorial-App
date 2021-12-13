package ranjih.kotlinandroid.view.adapter;

import android.app.Activity;
import android.content.Intent;
import androidx.percentlayout.widget.PercentRelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ranjih.kotlinandroid.R;
import ranjih.kotlinandroid.controller.utils.AppUtils;
import ranjih.kotlinandroid.controller.utils.Keys;
import ranjih.kotlinandroid.controller.utils.SharePref;
import ranjih.kotlinandroid.view.activity.WebViewActivity;
import ranjih.kotlinandroid.view.activity.YoutubeActivity;


/**
 * Created by Ram on 6/26/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = NewsAdapter.class.getSimpleName();


    private List<DocumentSnapshot> mData = new ArrayList<>();
    private static final int DEFAULT_VIEW_TYPE = 1;
    private static final int NATIVE_AD_VIEW_TYPE = 2;
    private Activity mContext;

    public NewsAdapter(Activity context, Task<QuerySnapshot> data) {
        mContext = context;


        if (data != null && data.getResult().size() > 0) {

            int totalCount = data.getResult().size();
            int totalAds = totalCount / 3;
            int addedIndex = 0;
            totalCount = totalCount + totalAds + 1;
            Log.d(TAG, "NewsAdapter totalCount: " + totalCount);
            Log.d(TAG, "NewsAdapter totalAds: " + totalAds);
            for (int i = 0; i < totalCount; i++) {
                if (i == 0 || i % 3 == 0) {
                    mData.add(null);
                } else {

                    mData.add(data.getResult().getDocuments().get(addedIndex));
                    addedIndex++;
                }
            }
        }


    }

    @Override
    public int getItemViewType(int position) {
        if (position % 4 == 0) {
            return NATIVE_AD_VIEW_TYPE;
        }
        return DEFAULT_VIEW_TYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        switch (viewType) {
            default:
                view = layoutInflater
                        .inflate(R.layout.listrow_news, parent, false);
                return new ViewHolder(view);
            case NATIVE_AD_VIEW_TYPE:
                view = layoutInflater.inflate(R.layout.listrow_native_ads, parent, false);
                return new NativeAdViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Log.d(TAG, "onBindViewHolder: " + position);
        if ((holder instanceof NewsAdapter.NativeAdViewHolder)) {
            NativeAdViewHolder nativeAdViewHolder = (NativeAdViewHolder) holder;
            if (SharePref.getInstance().readBoolean(Keys.KEY_BANNER_AD_VISIBLITY)) {
                AdRequest.Builder adBuilder = new AdRequest.Builder();
/*            adBuilder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    // Check the LogCat to get your test device ID
                    .addTestDevice("4C9AED4DAD2042C84C4AE85B421D9648");*/
                AdRequest adRequest = adBuilder
                        .build();

                nativeAdViewHolder.mAdView.loadAd(adRequest);
            }
        } else {
            NewsAdapter.ViewHolder newsViewHolder = (ViewHolder) holder;
            DocumentSnapshot documentSnapshot = mData.get(position);

            Log.d(TAG, "onBindViewHolder: " + documentSnapshot);
            if (documentSnapshot == null)
                return;
            String youtubeId = "";
            final String title = (String) documentSnapshot.getData().get(Keys.NEWS_TITLE);
            final String newsURL = (String) documentSnapshot.getData().get(Keys.KEY_URL);
            String newsImageURL = (String) documentSnapshot.getData().get(Keys.NEWS_IMAGE_URL);
            final boolean isYoutubeUrl = (boolean) documentSnapshot.getData().get(Keys.NEWS_IS_YOUTUBE_URL);

            try {
                String source = (String) documentSnapshot.getData().get(Keys.KEY_SOURCE);

                String date= (String) documentSnapshot.getData().get(Keys.KEY_DATE);

                ((ViewHolder) holder).tvSource.setText(""+source);

                ((ViewHolder) holder).tvDate.setText(""+date);
            }
            catch (Exception e){
                Log.e(TAG, "onBindViewHolder: ",e );
            }

            if (isYoutubeUrl) {
                youtubeId = AppUtils.getYoutubeVideoId(newsURL);
            ((ViewHolder) holder).ivPlayIcon.setVisibility(View.VISIBLE);
            }
            newsViewHolder.tvTitle.setText(title);
            Log.d(TAG, "onBindViewHolder: " + position + ":" + youtubeId);

            String path = "https://img.youtube.com/vi/" + youtubeId + "/default.jpg";
            if (isYoutubeUrl) {
                newsImageURL = path;
            }
            try {
                Picasso.get().load(newsImageURL).placeholder(R.drawable.news_bg).into(newsViewHolder.ivYoutubeThumbnail);
            } catch (Exception e) {
                Log.e(TAG, "onBindViewHolder: ", e);
            }
            final String finalYoutubeId = youtubeId;
            newsViewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        if (AppUtils.isNetworkAvailable(mContext)) {
                            if (isYoutubeUrl) {
                                Intent intent = new Intent(mContext, YoutubeActivity.class);
                                intent.putExtra(Keys.KEY_YOUTUBE_ID, finalYoutubeId);
                                mContext.startActivity(intent);
                            } else {
                                Intent intent = new Intent(mContext, WebViewActivity.class);
                                intent.putExtra(Keys.KEY_URL, newsURL);
                                intent.putExtra(Keys.NEWS_TITLE,title);
                                mContext.startActivity(intent);
                            }
                        } else {
                            Toast.makeText(mContext, mContext.getString(R.string.internet_warning), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "onClick: ", e);
                    }
                }
            });

            newsViewHolder.ivShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppUtils.shareNews(title,newsURL,mContext);
                }
            });
        }
    }


    @Override
    public int getItemCount() {

        if (mData == null)
            return 0;

        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivYoutubeThumbnail,ivPlayIcon,ivShare;
        private TextView tvTitle,tvSource,tvDate;
        private PercentRelativeLayout rootView;

        public ViewHolder(View itemView) {
            super(itemView);
            ivYoutubeThumbnail = (ImageView) itemView.findViewById(R.id.vt_iv_youtube_thumbnail);
            ivPlayIcon=itemView.findViewById(R.id.iv_play_icon);
            ivShare=itemView.findViewById(R.id.iv_share);
            tvTitle = (TextView) itemView.findViewById(R.id.vt_tv_title);
            tvDate=itemView.findViewById(R.id.tv_news_date);
            tvSource=itemView.findViewById(R.id.tv_news_source);
            rootView = (PercentRelativeLayout) itemView.findViewById(R.id.vt_root_view);

        }
    }

    public class NativeAdViewHolder extends RecyclerView.ViewHolder {


        AdView mAdView;

        public NativeAdViewHolder(View itemView) {
            super(itemView);

            mAdView = itemView.findViewById(R.id.adView);

        }
    }

}
