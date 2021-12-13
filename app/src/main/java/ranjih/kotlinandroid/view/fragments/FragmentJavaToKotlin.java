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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ranjih.kotlinandroid.R;
import ranjih.kotlinandroid.controller.utils.AppUtils;
import ranjih.kotlinandroid.controller.utils.FabScrollListener;
import ranjih.kotlinandroid.controller.utils.HtmlFileKeys;
import ranjih.kotlinandroid.controller.utils.Keys;
import ranjih.kotlinandroid.model.ComparisionTemplate;
import ranjih.kotlinandroid.view.adapter.RecyclerViewAdapter;
import ranjih.kotlinandroid.view.callbacks.RecyclerViewCallBack;

/**
 * Created by Ranjithkumar on 01-06-2017.
 */

public class FragmentJavaToKotlin extends BaseFragment implements RecyclerViewCallBack {


	private Context mContext;

	private RecyclerView mRecyclerView;
	private RecyclerViewAdapter mRecyclerViewAdapter;
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_java_to_kotlin, container, false);

	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mContext = getActivity();
		mRecyclerView = (RecyclerView) view.findViewById(R.id.fjk_recylerview);
		//loadPojoClassObj("jk_data_types");

		setRecylerAdapter();
	}



	private void setRecylerAdapter() {
		ArrayList<String> titles = new ArrayList<>();
		titles.add(getString(R.string.java_to_kotlin1));
		titles.add(getString(R.string.java_to_kotlin2));
		titles.add(getString(R.string.java_to_kotlin3));
		titles.add(getString(R.string.java_to_kotlin4));
		titles.add(getString(R.string.java_to_kotlin5));
		titles.add(getString(R.string.java_to_kotlin6));
		titles.add(getString(R.string.java_to_kotlin7));
		titles.add(getString(R.string.java_to_kotlin8));
        titles.add(getString(R.string.java_to_kotlin9));

		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
		mRecyclerView.setLayoutManager(layoutManager);
		mRecyclerViewAdapter = new RecyclerViewAdapter(titles, this);
		mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.addOnScrollListener(new FabScrollListener(getActivity()));

	}


    private void loadPojoClassObj(int position, String key) {
        int resId = getResources().getIdentifier(key, "array", getActivity().getPackageName());
		List<String> strArrays = Arrays.asList(getResources().getStringArray(resId));
		if (strArrays.size() >= 3) {
			ComparisionTemplate comparisionTemplate = new ComparisionTemplate();
			comparisionTemplate.setTitle1(getString(R.string.java));
			comparisionTemplate.setTitle2(getString(R.string.kotlin));
			comparisionTemplate.setMainTitle(strArrays.get(0));
			comparisionTemplate.setCode1(strArrays.get(1));
			comparisionTemplate.setCode2(strArrays.get(2));
            switchWebViewFragment(new FragmentWebView(), position, Keys.KEY_ASSET_PATH + "comparision_template.html", comparisionTemplate);
        }
	}

    public void switchWebViewFragment(Fragment fragment, int position, String url, ComparisionTemplate comparisionTemplate) {

		if (fragment != null) {
			Bundle bundle = new Bundle();
            bundle.putInt(Keys.KEY_POSITION, position);
            bundle.putString(Keys.KEY_URL, url);
			bundle.putSerializable(Keys.KEY_COMPARISION_TEMPLATE, comparisionTemplate);
			fragment.setArguments(bundle);
			// Insert the fragment by replacing any existing fragment

			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.flContent, fragment).addToBackStack(null).commit();
		}
		AppUtils.setFullScreen(getActivity());
	}


	@Override
	public void onItemClick(int position, String value) {
        if (HtmlFileKeys.getInstance().getmJavaToKotlinKeys().get(position) != null)
            loadPojoClassObj(position, HtmlFileKeys.getInstance().getmJavaToKotlinKeys().get(position));
    }
}
