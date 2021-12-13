package ranjih.kotlinandroid.view.fragments;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ranjih.kotlinandroid.BuildConfig;
import ranjih.kotlinandroid.R;


public class FragmentAbout extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onViewCreated(View rootView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);
        TextView tvVersionName = (TextView) rootView.findViewById(R.id.tv_about_versionname);
        try {
        /*version name*/

            String versionName = BuildConfig.VERSION_NAME;

            tvVersionName.setText(getActivity().getResources().getString(R.string.version) + versionName);
        /*version code*/
            int versionCode = BuildConfig.VERSION_CODE;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
