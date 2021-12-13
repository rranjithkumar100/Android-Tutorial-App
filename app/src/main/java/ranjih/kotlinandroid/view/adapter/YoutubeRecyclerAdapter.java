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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import ranjih.kotlinandroid.R;
import ranjih.kotlinandroid.controller.utils.AppUtils;
import ranjih.kotlinandroid.controller.utils.Keys;
import ranjih.kotlinandroid.view.activity.YoutubeActivity;


/**
 * Created by Ram on 6/26/2017.
 */

public class YoutubeRecyclerAdapter extends RecyclerView.Adapter<YoutubeRecyclerAdapter.ViewHolder> {
    private static final String TAG = YoutubeRecyclerAdapter.class.getSimpleName();
    private LinkedHashMap<String, String> mData;
    private Activity mContext;

    public YoutubeRecyclerAdapter(Activity context, LinkedHashMap<String, String> youtubeVideosMap) {
        mContext = context;
        mData = youtubeVideosMap;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View childView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow_video_tutorial, parent, false);
        return new ViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String title = (new ArrayList<>(mData.values())).get(position);
        String youtubeURL = (new ArrayList<>(mData.keySet())).get(position);
        final String youtubeId = AppUtils.getYoutubeVideoId(youtubeURL);
        holder.tvTitle.setText(title);
       // Log.d(TAG, "onBindViewHolder: " + position + ":" + youtubeId);

        String path = "https://img.youtube.com/vi/" + youtubeId + "/default.jpg";
        try {
            Picasso.get().load(path).into(holder.ivYoutubeThumbnail);
        } catch (Exception e) {
            Log.e(TAG, "onBindViewHolder: ", e);
        }
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (AppUtils.isNetworkAvailable(mContext)) {
                        Intent intent = new Intent(mContext, YoutubeActivity.class);
                        intent.putExtra(Keys.KEY_YOUTUBE_ID, youtubeId);
                        mContext.startActivity(intent);
                    } else {
                        Toast.makeText(mContext, mContext.getString(R.string.internet_warning), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e(TAG, "onClick: ", e);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivYoutubeThumbnail;
        private TextView tvTitle;
        private PercentRelativeLayout rootView;

        public ViewHolder(View itemView) {
            super(itemView);
            ivYoutubeThumbnail = (ImageView) itemView.findViewById(R.id.vt_iv_youtube_thumbnail);
            tvTitle = (TextView) itemView.findViewById(R.id.vt_tv_title);
            rootView = (PercentRelativeLayout) itemView.findViewById(R.id.vt_root_view);
        }
    }
}
