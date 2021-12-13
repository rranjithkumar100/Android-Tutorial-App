package ranjih.kotlinandroid.view.adapter;

import java.util.ArrayList;

public class ExpandableHeaderData {

	private String name;
	private String url;
	private ArrayList<ExpandableChildData> childList = new ArrayList<ExpandableChildData>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<ExpandableChildData> getChildList() {
		return childList;
	}

	public void setChildList(ArrayList<ExpandableChildData> productList) {
		this.childList = productList;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String id) {
		this.url = id;
	}
}