package ranjih.kotlinandroid.view.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ranjih.kotlinandroid.R;
import ranjih.kotlinandroid.view.callbacks.RecyclerViewCallBack;

/**
 * Created by Ranjithkumar on 03-06-2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
	private ArrayList<String> mTitles;
	private RecyclerViewCallBack mRecyclerViewCallBack;

	public RecyclerViewAdapter(ArrayList<String> list, RecyclerViewCallBack recyclerViewCallBack) {
		mTitles = list;
		mRecyclerViewCallBack = recyclerViewCallBack;
	}

	public void refreshList(ArrayList<String> list, RecyclerViewCallBack recyclerViewCallBack) {
		mTitles.clear();
		mTitles.addAll(list);
		mRecyclerViewCallBack = recyclerViewCallBack;
		notifyDataSetChanged();
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View childView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_single_line, parent, false);
		return new ViewHolder(childView);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, final int position) {
		holder.tvTitle.setText(mTitles.get(position));
		holder.tvTitle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mRecyclerViewCallBack.onItemClick(position, mTitles.get(position));
			}
		});
	}

	@Override
	public int getItemCount() {
		if (mTitles != null)
			return mTitles.size();
		else
			return 0;
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		private TextView tvTitle;

		public ViewHolder(View itemView) {
			super(itemView);
			tvTitle = (TextView) itemView.findViewById(R.id.listrow_tv_title);
		}
	}
}
