package ranjih.kotlinandroid.controller.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ranjih.kotlinandroid.App;
import ranjih.kotlinandroid.R;

import static ranjih.kotlinandroid.controller.utils.Keys.KEY_ASSET_PATH;

/**
 * Created by Ram on 8/6/2017.
 */

public class HtmlFileKeys {
    private static final HtmlFileKeys ourInstance = new HtmlFileKeys();
    private List<String> mJavaToKotlinKeys;
    private List<String> mAndroidTutorialKeys;
    private LinkedList<String> mKotlinBasicsUrls;
    private ArrayList<String> mAndroidTutorialTitles;
    private LinkedList<String> mInterviewQtns;
    private LinkedList<String> mInterviewAns;

    private HtmlFileKeys() {
        setKotlinBasicsKeys();
        setJavaVsKotlinKeys();
        setAndroidTutorialKeys();
        setAndroidTutorialTitles();
        setInterviewQtnTitles();
        setInterviewAnswers();
    }

    public static HtmlFileKeys getInstance() {
        return ourInstance;
    }

    public List<String> getmJavaToKotlinKeys() {
        return mJavaToKotlinKeys;
    }

    public LinkedList<String> getmKotlinBasicsUrls() {
        return mKotlinBasicsUrls;
    }

    public List<String> getmAndroidTutorialKeys() {
        return mAndroidTutorialKeys;
    }

    public ArrayList<String> getmAndroidTutorialTitles() {
        return mAndroidTutorialTitles;
    }

    public LinkedList<String> getInterviewQtns() {
        return mInterviewQtns;
    }

    public LinkedList<String> getInterviewAnswers() {
        return mInterviewAns;
    }

    private void setJavaVsKotlinKeys() {
        mJavaToKotlinKeys = new ArrayList<>();
        mJavaToKotlinKeys.add("jk_data_types");
        mJavaToKotlinKeys.add("jk_enum");
        mJavaToKotlinKeys.add("jk_for_loop");
        mJavaToKotlinKeys.add("jk_switch_case");
        mJavaToKotlinKeys.add("jk_exception_handling");
        mJavaToKotlinKeys.add("jk_constructor");
        mJavaToKotlinKeys.add("jk_inheritance");
        mJavaToKotlinKeys.add("jk_interface");
        mJavaToKotlinKeys.add("jk_ternary");
    }

    private void setAndroidTutorialKeys() {
        mAndroidTutorialKeys = new ArrayList<>();
        mAndroidTutorialKeys.add("ap_activity");
        mAndroidTutorialKeys.add("ap_fragment");
        mAndroidTutorialKeys.add("ap_service");
        mAndroidTutorialKeys.add("ap_textview_edittext");
        mAndroidTutorialKeys.add("ap_spinner");
        mAndroidTutorialKeys.add("ap_alert_dialog");
        mAndroidTutorialKeys.add("ap_shared_pref");
        mAndroidTutorialKeys.add("ap_onclick");
        mAndroidTutorialKeys.add("ap_intent");
        mAndroidTutorialKeys.add("ap_switch_fragment");
        mAndroidTutorialKeys.add("ap_network_availablity");
        //mKeys.add("ap_set_full_screen");
        mAndroidTutorialKeys.add("ap_hide_keyboard");

    }

    private void setAndroidTutorialTitles() {
        mAndroidTutorialTitles = new ArrayList<>();
        mAndroidTutorialTitles.add("Activity");
        mAndroidTutorialTitles.add("Fragments");
        mAndroidTutorialTitles.add("Service");
        mAndroidTutorialTitles.add("Textview & EditText");
        mAndroidTutorialTitles.add("Spinner & ArrayAdapter");
        mAndroidTutorialTitles.add("Alert Dialog");
        mAndroidTutorialTitles.add("Shared Preferences");
        mAndroidTutorialTitles.add("OnClickListener");
        mAndroidTutorialTitles.add("Switch Activity");
        mAndroidTutorialTitles.add("Switch Fragment");
        mAndroidTutorialTitles.add("Network Availability");
        //mAndroidTutorialTitles.add("Full Screen");
        mAndroidTutorialTitles.add("Hide Keyboard");
    }


    private void setInterviewQtnTitles() {
        mInterviewQtns = new LinkedList<>();
        mInterviewQtns.add(App.getContext().getString(R.string.interview_qtn1));
        mInterviewQtns.add(App.getContext().getString(R.string.interview_qtn2));
        mInterviewQtns.add(App.getContext().getString(R.string.interview_qtn3));
        mInterviewQtns.add(App.getContext().getString(R.string.interview_qtn4));
        mInterviewQtns.add(App.getContext().getString(R.string.interview_qtn5));
        mInterviewQtns.add(App.getContext().getString(R.string.interview_qtn6));
        mInterviewQtns.add(App.getContext().getString(R.string.interview_qtn7a)+
                App.getContext().getString(R.string.interview_qtn7b));
        mInterviewQtns.add(App.getContext().getString(R.string.interview_qtn8));
        mInterviewQtns.add(App.getContext().getString(R.string.interview_qtn9));
        mInterviewQtns.add(App.getContext().getString(R.string.interview_qtn10));
        mInterviewQtns.add(App.getContext().getString(R.string.interview_qtn11));
        mInterviewQtns.add(App.getContext().getString(R.string.interview_qtn12));

        mInterviewQtns.add(App.getContext().getString(R.string.interview_qtn13));
        mInterviewQtns.add(App.getContext().getString(R.string.interview_qtn14));
        mInterviewQtns.add(App.getContext().getString(R.string.interview_qtn15));
        mInterviewQtns.add(App.getContext().getString(R.string.interview_qtn16));
        mInterviewQtns.add(App.getContext().getString(R.string.interview_qtn17));
        mInterviewQtns.add(App.getContext().getString(R.string.interview_qtn18));
        mInterviewQtns.add(App.getContext().getString(R.string.interview_qtn19));
        mInterviewQtns.add(App.getContext().getString(R.string.interview_qtn20));
    }

    private void setInterviewAnswers() {
        mInterviewAns = new LinkedList<>();
        mInterviewAns.add(App.getContext().getString(R.string.interview_ans1));
        mInterviewAns.add(App.getContext().getString(R.string.interview_ans2));
        mInterviewAns.add(App.getContext().getString(R.string.interview_ans3));
        mInterviewAns.add(App.getContext().getString(R.string.interview_ans4) + App.getContext().getString(R.string.interview_ans4b));
        mInterviewAns.add(App.getContext().getString(R.string.interview_ans5));
        mInterviewAns.add(App.getContext().getString(R.string.interview_ans6));
        mInterviewAns.add(App.getContext().getString(R.string.interview_ans7));
        mInterviewAns.add(App.getContext().getString(R.string.interview_ans8));
        mInterviewAns.add(App.getContext().getString(R.string.interview_ans9));
        mInterviewAns.add(App.getContext().getString(R.string.interview_ans10));
        mInterviewAns.add(App.getContext().getString(R.string.interview_ans11));
        mInterviewAns.add(App.getContext().getString(R.string.interview_ans12));
        mInterviewAns.add(App.getContext().getString(R.string.interview_ans13));
        mInterviewAns.add(App.getContext().getString(R.string.interview_ans14));
        mInterviewAns.add(App.getContext().getString(R.string.interview_ans15));
        mInterviewAns.add(App.getContext().getString(R.string.interview_ans16));
        mInterviewAns.add(App.getContext().getString(R.string.interview_ans17));
        mInterviewAns.add(App.getContext().getString(R.string.interview_ans18));
        mInterviewAns.add(App.getContext().getString(R.string.interview_ans19));
        mInterviewAns.add(App.getContext().getString(R.string.interview_ans20));
    }

    private void setKotlinBasicsKeys() {
        mKotlinBasicsUrls = new LinkedList<>();
        mKotlinBasicsUrls.add(KEY_ASSET_PATH + "new_introduction.html");
        mKotlinBasicsUrls.add(KEY_ASSET_PATH + "new_server_overview.html");
        mKotlinBasicsUrls.add(KEY_ASSET_PATH + "new_android_overview.html");


        mKotlinBasicsUrls.add(KEY_ASSET_PATH + "new_basic_syntax.html");
        mKotlinBasicsUrls.add(KEY_ASSET_PATH + "new_idioms.html");
        mKotlinBasicsUrls.add(KEY_ASSET_PATH + "new_coding_conventions.html");

        mKotlinBasicsUrls.add(KEY_ASSET_PATH + "new_basictypes.html");
        mKotlinBasicsUrls.add(KEY_ASSET_PATH + "new_control_flow.html");
        mKotlinBasicsUrls.add(KEY_ASSET_PATH + "new_returns.html");

        mKotlinBasicsUrls.add(KEY_ASSET_PATH + "new_classes.html");
        mKotlinBasicsUrls.add(KEY_ASSET_PATH + "new_properties_and_fields.html");
        mKotlinBasicsUrls.add(KEY_ASSET_PATH + "new_interface.html");
        mKotlinBasicsUrls.add(KEY_ASSET_PATH + "new_data_classes.html");
        mKotlinBasicsUrls.add(KEY_ASSET_PATH + "new_sealed_classes.html");

        mKotlinBasicsUrls.add(KEY_ASSET_PATH + "new_generics.html");
        mKotlinBasicsUrls.add(KEY_ASSET_PATH + "new_nested_class.html");
        mKotlinBasicsUrls.add(KEY_ASSET_PATH + "new_enumclass.html");
        mKotlinBasicsUrls.add(KEY_ASSET_PATH + "new_delegation.html");
        mKotlinBasicsUrls.add(KEY_ASSET_PATH + "new_delegation_properties.html");

        mKotlinBasicsUrls.add(KEY_ASSET_PATH + "new_functions.html");
        mKotlinBasicsUrls.add(KEY_ASSET_PATH + "new_lambdas.html");
        mKotlinBasicsUrls.add(KEY_ASSET_PATH + "new_inline_functions.html");

        mKotlinBasicsUrls.add(KEY_ASSET_PATH + "new_collections.html");
        mKotlinBasicsUrls.add(KEY_ASSET_PATH + "new_ranges.html");
        mKotlinBasicsUrls.add(KEY_ASSET_PATH + "new_equality.html");
        mKotlinBasicsUrls.add(KEY_ASSET_PATH + "new_operator_overloading.html");
        mKotlinBasicsUrls.add(KEY_ASSET_PATH + "new_null_safty.html");

    }

}
