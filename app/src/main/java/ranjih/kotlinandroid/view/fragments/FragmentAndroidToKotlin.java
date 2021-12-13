package ranjih.kotlinandroid.view.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ranjih.kotlinandroid.R;
import ranjih.kotlinandroid.controller.utils.AppUtils;
import ranjih.kotlinandroid.controller.utils.FabScrollListener;
import ranjih.kotlinandroid.controller.utils.HtmlFileKeys;
import ranjih.kotlinandroid.controller.utils.Keys;
import ranjih.kotlinandroid.model.AndroidProgramsTemplate;
import ranjih.kotlinandroid.view.adapter.RecyclerViewAdapter;
import ranjih.kotlinandroid.view.callbacks.RecyclerViewCallBack;

/**
 * Created by Ram on 6/4/2017.
 */

public class FragmentAndroidToKotlin extends BaseFragment implements RecyclerViewCallBack {
    private static final String TAG = FragmentAndroidToKotlin.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private Context mContext;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_android_programs, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getActivity();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fap_recylerview);
        //loadPojoClassObj("jk_data_types");

        setRecylerAdapter();
    }


    private void setRecylerAdapter() {



        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerViewAdapter = new RecyclerViewAdapter(HtmlFileKeys.getInstance().getmAndroidTutorialTitles(), this);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.addOnScrollListener(new FabScrollListener(getActivity()));

    }

    public void switchWebViewFragment(Fragment fragment, int position, String url, AndroidProgramsTemplate androidProgramsTemplate) {

        if (fragment != null) {
            Bundle bundle = new Bundle();
            bundle.putInt(Keys.KEY_POSITION, position);
            bundle.putString(Keys.KEY_URL, url);
            bundle.putSerializable(Keys.KEY_ANDROID_PGMS_TEMPLATE, androidProgramsTemplate);
            fragment.setArguments(bundle);
            // Insert the fragment by replacing any existing fragment

            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).addToBackStack(null).commit();
        }
        AppUtils.setFullScreen(getActivity());
    }

    private void loadPojoClassObj(int position, String key, String title) {
        int resId = getResources().getIdentifier(key, "string", getActivity().getPackageName());
        String code = getString(resId);
        if (code != null && code.length() > 0) {
            AndroidProgramsTemplate androidProgramsTemplate = new AndroidProgramsTemplate();
            androidProgramsTemplate.setTitle(title);
            androidProgramsTemplate.setCode(code);
            switchWebViewFragment(new FragmentWebView(), position, Keys.KEY_ASSET_PATH + "android_programs_template.html", androidProgramsTemplate);
        }
    }

    @Override
    public void onItemClick(int position, String value) {
        if (HtmlFileKeys.getInstance().getmAndroidTutorialKeys().get(position) != null)
            loadPojoClassObj(position, HtmlFileKeys.getInstance().getmAndroidTutorialKeys().get(position),
                    HtmlFileKeys.getInstance().getmAndroidTutorialTitles().get(position));
    }
}

