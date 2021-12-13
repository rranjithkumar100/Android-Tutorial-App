package ranjih.kotlinandroid.view.fragments;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ranjih.kotlinandroid.R;
import ranjih.kotlinandroid.controller.utils.FabScrollListener;
import ranjih.kotlinandroid.controller.utils.HtmlFileKeys;
import ranjih.kotlinandroid.view.adapter.InterviewAdapter;

/**
 * Created by Ram on 6/4/2017.
 */

public class FragmentKotlinInterviewQtns extends BaseFragment  {
    private static final String TAG = FragmentKotlinInterviewQtns.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private InterviewAdapter mRecyclerViewAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_interview_qtns, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.fap_recylerview);

        setRecylerAdapter();
    }


    private void setRecylerAdapter() {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerViewAdapter = new InterviewAdapter(HtmlFileKeys.getInstance().getInterviewQtns(),HtmlFileKeys.getInstance().getInterviewAnswers());
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.addOnScrollListener(new FabScrollListener(getActivity()));

    }


}

