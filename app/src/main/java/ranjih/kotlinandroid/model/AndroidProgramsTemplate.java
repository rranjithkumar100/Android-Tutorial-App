package ranjih.kotlinandroid.model;

import java.io.Serializable;

/**
 * Created by Ram on 6/4/2017.
 */

public class AndroidProgramsTemplate implements Serializable {
    private String title;
    private String code;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
