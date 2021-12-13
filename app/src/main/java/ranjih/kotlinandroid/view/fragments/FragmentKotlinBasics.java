package ranjih.kotlinandroid.view.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import ranjih.kotlinandroid.R;
import ranjih.kotlinandroid.controller.utils.AppUtils;
import ranjih.kotlinandroid.controller.utils.HtmlFileKeys;
import ranjih.kotlinandroid.controller.utils.Keys;
import ranjih.kotlinandroid.view.adapter.ExpandableChildData;
import ranjih.kotlinandroid.view.adapter.ExpandableHeaderData;
import ranjih.kotlinandroid.view.adapter.ExpandableListAdapter;
import ranjih.kotlinandroid.view.callbacks.ExpandableListCallBack;

/**
 * Created by Ranjithkumar on 01-06-2017.
 */

public class FragmentKotlinBasics extends BaseFragment implements ExpandableListCallBack {

	private static final String TAG = FragmentKotlinBasics.class.getSimpleName();
	private LinkedHashMap<String, ExpandableHeaderData> mHeaderDataLinkedHashMap = new LinkedHashMap<>();
	private ArrayList<ExpandableHeaderData> mHeaderDatas = new ArrayList<>();
	private ExpandableListAdapter mExpandableListAdapter;
	private ExpandableListView mExpandableListView;
	private Context mContext;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_kotlin_basics, container, false);

	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mContext = getActivity();
		mExpandableListView = (ExpandableListView) view.findViewById(R.id.fkb_exlv_kotlin_basics);
		mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
			int previousItem = -1;

			@Override
			public void onGroupExpand(int groupPosition) {
				if (groupPosition != previousItem) {
					mExpandableListView.collapseGroup(previousItem);
					previousItem = groupPosition;
				}
			}
		});
		getActivity().setTitle(getString(R.string.kotlin_basics));
		loadData();
		setAdapter();
	}

	private void setAdapter() {

		mExpandableListAdapter = new ExpandableListAdapter(mContext, mHeaderDatas, this);
		mExpandableListView.setAdapter(mExpandableListAdapter);
		mExpandableListView.setItemChecked(0, true);//for click the home menu (0th position)

	}

	private void clearData() {
		if (mHeaderDatas != null)
			mHeaderDatas.clear();
		if (mHeaderDataLinkedHashMap != null)
			mHeaderDataLinkedHashMap.clear();
	}

	private void loadData() {
		clearData();
		LinkedList<String> kotlinBasicsUrls = HtmlFileKeys.getInstance().getmKotlinBasicsUrls();
		addExpandableListItems("Introduction", null, "What is Kotlin?", kotlinBasicsUrls.get(0), 0);
		addExpandableListItems("Introduction", null, getString(R.string.kotlin_for_server_side), kotlinBasicsUrls.get(1), 1);
		addExpandableListItems("Introduction", null, getString(R.string.kotlin_for_android), kotlinBasicsUrls.get(2), 2);


		addExpandableListItems(getString(R.string.getting_started), null, getString(R.string.basic_syntax), kotlinBasicsUrls.get(3), 3);
		addExpandableListItems(getString(R.string.getting_started), null, getString(R.string.idioms), kotlinBasicsUrls.get(4), 4);
		addExpandableListItems(getString(R.string.getting_started), null, getString(R.string.coding_conventions), kotlinBasicsUrls.get(5), 5);

		addExpandableListItems(getString(R.string.basics), null, getString(R.string.basic_types), kotlinBasicsUrls.get(6), 6);
		addExpandableListItems(getString(R.string.basics), null, getString(R.string.loops_if_condition), kotlinBasicsUrls.get(7), 7);
		addExpandableListItems(getString(R.string.basics), null, getString(R.string.return_and_jumps), kotlinBasicsUrls.get(8), 8);

		addExpandableListItems(getString(R.string.classes_and_objects), null, "Classes", kotlinBasicsUrls.get(9), 9);
		addExpandableListItems(getString(R.string.classes_and_objects), null, "Properties and Fields", kotlinBasicsUrls.get(10), 10);
		addExpandableListItems(getString(R.string.classes_and_objects), null, "Interface", kotlinBasicsUrls.get(11), 11);
		addExpandableListItems(getString(R.string.classes_and_objects), null, "Data classes", kotlinBasicsUrls.get(12), 12);
		addExpandableListItems(getString(R.string.classes_and_objects), null, "Sealed classes", kotlinBasicsUrls.get(13), 13);

		addExpandableListItems(getString(R.string.classes_and_objects), null, "Generics", kotlinBasicsUrls.get(14), 14);
		addExpandableListItems(getString(R.string.classes_and_objects), null, "Nested Classes", kotlinBasicsUrls.get(15), 15);
		addExpandableListItems(getString(R.string.classes_and_objects), null, "Enum Classes", kotlinBasicsUrls.get(16), 16);
		addExpandableListItems(getString(R.string.classes_and_objects), null, "Delegation", kotlinBasicsUrls.get(17), 17);
		addExpandableListItems(getString(R.string.classes_and_objects), null, "Delegated Properties", kotlinBasicsUrls.get(18), 18);

		addExpandableListItems("Functions and Lambdas", null, "Functions", kotlinBasicsUrls.get(19), 19);
		addExpandableListItems("Functions and Lambdas", null, "Lambdas", kotlinBasicsUrls.get(20), 20);
		addExpandableListItems("Functions and Lambdas", null, "Inline Functions", kotlinBasicsUrls.get(21), 21);

		addExpandableListItems("Other", null, "Collections", kotlinBasicsUrls.get(22), 22);
		addExpandableListItems("Other", null, "Ranges", kotlinBasicsUrls.get(23), 23);
		addExpandableListItems("Other", null, "Equality", kotlinBasicsUrls.get(24), 24);
		addExpandableListItems("Other", null, "Operator Overloading", kotlinBasicsUrls.get(25), 25);
		addExpandableListItems("Other", null, "Null Safety", kotlinBasicsUrls.get(26), 26);


	}

	private void addExpandableListItems(String headerName, String headerURL, String childName, String childURL, int urlPosition) {

		//check the hash map if the group already exists
		ExpandableHeaderData headerInfo = mHeaderDataLinkedHashMap.get(headerName);
		//add the group if doesn't exists
		if (headerInfo == null) {
			headerInfo = new ExpandableHeaderData();
			headerInfo.setName(headerName);
			headerInfo.setUrl(headerURL);
			mHeaderDataLinkedHashMap.put(headerName, headerInfo);
			mHeaderDatas.add(headerInfo);
		}
		if (childName != null) {
			//get the children for the group
			ArrayList<ExpandableChildData> childList = headerInfo.getChildList();
			//create a new child and add that to the group
			ExpandableChildData childInfo = new ExpandableChildData();
			childInfo.setName(childName);
			childInfo.setUrl(childURL);
			childInfo.setUrlPosition(urlPosition);
			childList.add(childInfo);
			headerInfo.setChildList(childList);
		}
	}

	@Override
	public void itemClicked(String url, int position) {
		switchWebViewFragment(new FragmentWebView(), url, position);
	}

	public void switchWebViewFragment(Fragment fragment, String url, int position) {

		if (fragment != null) {
			Bundle bundle = new Bundle();
			bundle.putBoolean(Keys.KEY_IS_KOTLIN_BASICS, true);
			bundle.putInt(Keys.KEY_POSITION, position);
			bundle.putString(Keys.KEY_URL, url);
			fragment.setArguments(bundle);
			// Insert the fragment by replacing any existing fragment

			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.flContent, fragment).addToBackStack(null).commit();
		}
		AppUtils.setFullScreen(getActivity());
	}
}
