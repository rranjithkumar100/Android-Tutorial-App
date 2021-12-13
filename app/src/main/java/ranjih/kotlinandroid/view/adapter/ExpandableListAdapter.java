package ranjih.kotlinandroid.view.adapter;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ranjih.kotlinandroid.R;
import ranjih.kotlinandroid.view.callbacks.ExpandableListCallBack;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

	private Context context;
	private ArrayList<ExpandableHeaderData> headerList;
	private ExpandableListCallBack expandableListCallBack;

	public ExpandableListAdapter(Context context, ArrayList<ExpandableHeaderData> headerList, ExpandableListCallBack expandableListCallBack) {
		this.context = context;
		this.headerList = headerList;
		this.expandableListCallBack = expandableListCallBack;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		ArrayList<ExpandableChildData> childList = headerList.get(groupPosition).getChildList();
		return childList.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
	                         View view, ViewGroup parent) {

		final ExpandableChildData expandableChildData = (ExpandableChildData) getChild(groupPosition, childPosition);
		if (view == null) {
			LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = infalInflater.inflate(R.layout.custom_expandable_child, null);
		}


		TextView childItem = (TextView) view.findViewById(R.id.item_name);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			childItem.setText(Html.fromHtml(expandableChildData.getName().trim(), Html.FROM_HTML_MODE_LEGACY));
		} else {
			childItem.setText(Html.fromHtml(expandableChildData.getName().trim()));
		}
		view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				expandableListCallBack.itemClicked(expandableChildData.getUrl(), expandableChildData.getUrlPosition());
			}
		});

		return view;
	}

	@Override
	public int getChildrenCount(int groupPosition) {

		ArrayList<ExpandableChildData> childList = headerList.get(groupPosition).getChildList();
		return childList.size();

	}

	@Override
	public Object getGroup(int groupPosition) {
		return headerList.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return headerList.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isLastChild, View view,
	                         ViewGroup parent) {

		ExpandableHeaderData expandableHeaderData = (ExpandableHeaderData) getGroup(groupPosition);
		if (view == null) {
			LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inf.inflate(R.layout.custom_expandable_header, null);
		}

		TextView heading = (TextView) view.findViewById(R.id.heading);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			heading.setText(Html.fromHtml(expandableHeaderData.getName().trim(), Html.FROM_HTML_MODE_LEGACY));
		} else {
			heading.setText(Html.fromHtml(expandableHeaderData.getName().trim()));
		}
		return view;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}


}