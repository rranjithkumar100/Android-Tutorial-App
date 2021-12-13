package ranjih.kotlinandroid.model;

import java.io.Serializable;

/**
 * Created by Ranjithkumar on 02-06-2017.
 */

public class ComparisionTemplate implements Serializable {
	private String mainTitle;
	private String title1;
	private String code1;
	private String title2;
	private String code2;

	public String getTitle1() {
		return title1;
	}

	public void setTitle1(String title1) {
		this.title1 = title1;
	}

	public String getCode1() {
		return code1;
	}

	public void setCode1(String code1) {
		this.code1 = code1;
	}

	public String getTitle2() {
		return title2;
	}

	public void setTitle2(String title2) {
		this.title2 = title2;
	}

	public String getCode2() {
		return code2;
	}

	public void setCode2(String code2) {
		this.code2 = code2;
	}

	public String getMainTitle() {
		return mainTitle;
	}

	public void setMainTitle(String mainTitle) {
		this.mainTitle = mainTitle;
	}
}
