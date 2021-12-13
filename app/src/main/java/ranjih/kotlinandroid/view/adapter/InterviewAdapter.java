package ranjih.kotlinandroid.view.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

import ranjih.kotlinandroid.R;

/**
 * Created by Ranjithkumar on 03-06-2017.
 */

public class InterviewAdapter extends RecyclerView.Adapter<InterviewAdapter.ViewHolder> {
    private LinkedList<String> mQtns;
    private LinkedList<String> mAns;
    private LinkedList<Integer> mSelectedItems=new LinkedList<>();


    public InterviewAdapter(LinkedList<String> qtns, LinkedList<String> answers) {
        mQtns = qtns;
        mAns = answers;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View childView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow_interview_qtns, parent, false);
        return new ViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvQtn.setText(mQtns.get(position));
        holder.tvAns.setText("Ans: " + mAns.get(position));
        if(mSelectedItems.contains(position)){
            holder.tvAns.setVisibility(View.VISIBLE);
        }
        else {
            holder.tvAns.setVisibility(View.GONE);
        }
        holder.tvQtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mSelectedItems.contains(holder.getAdapterPosition())){
                    mSelectedItems.remove(mSelectedItems.indexOf(holder.getAdapterPosition()));
                }
                else {
                    mSelectedItems.add(holder.getAdapterPosition());
                }
                notifyDataSetChanged();
 /*               if (holder.tvAns.getVisibility() == View.VISIBLE)
                    holder.tvAns.setVisibility(View.GONE);
                else
                    holder.tvAns.setVisibility(View.VISIBLE);*/
            }
        });

        holder.tvAns.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(mSelectedItems.contains(holder.getAdapterPosition())){
                    mSelectedItems.remove(mSelectedItems.indexOf(holder.getAdapterPosition()));
                notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mQtns != null)
            return mQtns.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvQtn, tvAns;

        public ViewHolder(View itemView) {
            super(itemView);
            tvQtn = (TextView) itemView.findViewById(R.id.tv_interview_qtn);
            tvAns = (TextView) itemView.findViewById(R.id.tv_interview_ans);

        }
    }
}
