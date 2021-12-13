package ranjih.kotlinandroid.view.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedHashMap;

import ranjih.kotlinandroid.R;
import ranjih.kotlinandroid.controller.utils.FabScrollListener;
import ranjih.kotlinandroid.view.adapter.YoutubeRecyclerAdapter;

/**
 * Created by Ram on 6/25/2017.
 */

public class FragmentKotlinVideos extends BaseFragment {
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_video_tutorial, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fvt_recyclerview);

        setRecyclerAdapter();
    }

    private void setRecyclerAdapter() {
        LinkedHashMap<String, String> youtubeVideosMap = new LinkedHashMap<>();
        youtubeVideosMap.put("https://youtu.be/0isrdp5meyQ", "1.Introduction");
        youtubeVideosMap.put("https://youtu.be/3C-e4dTvrSI", "2.Hello world");
        youtubeVideosMap.put("https://youtu.be/glX5Yx63_Vs", "3.Android Studio 3");
        youtubeVideosMap.put("https://youtu.be/NpveMP5869U", "4.Class | Object");
        youtubeVideosMap.put("https://youtu.be/4YF_DUMQqFA", "5.Var & Val");
        youtubeVideosMap.put("https://youtu.be/e-gZc-mZqE8", "6.Kotlin Java Together");
        youtubeVideosMap.put("https://youtu.be/YuRjPUxvcek", "7.Convert Java Kotlin");
        youtubeVideosMap.put("https://youtu.be/8TW9zbgQnKc", "8.Decompile Bytecode");
        youtubeVideosMap.put("https://youtu.be/q_XZWtP6KQg", "9.Operators");
        youtubeVideosMap.put("https://youtu.be/9XmGh4WgfSg", "10.String Template");

        youtubeVideosMap.put("https://youtu.be/qaqKps5mNts", "11.If Else Expression");
        youtubeVideosMap.put("https://youtu.be/Fh4chRUL5kE", "12.String Comparison");
        youtubeVideosMap.put("https://youtu.be/AWM5cAipEp0", "13.Null Handling");
        youtubeVideosMap.put("https://youtu.be/TrUGG0nENMo", "14.When(Switch) Expression");
        youtubeVideosMap.put("https://youtu.be/bDmXJLV-HmA", "15.Loops and Range");
        youtubeVideosMap.put("https://youtu.be/FUqD6srpuPY", "16.List and Map");
        youtubeVideosMap.put("https://youtu.be/XOIC-aHyOag", "17.Function Expression");
        youtubeVideosMap.put("https://youtu.be/n0fdpvOdIbo", "18.Function calling from Java");
        youtubeVideosMap.put("https://youtu.be/fr1K_0duf78", "19.Default and Named Parameters");
        youtubeVideosMap.put("https://youtu.be/4tlsFbNLrl8", "20.String to Integer");

        youtubeVideosMap.put("https://youtu.be/RMe0v0JRuio", "21.Try Expression");
        youtubeVideosMap.put("https://youtu.be/JzaKJNt4eXk", "22.Extension Function");
        youtubeVideosMap.put("https://youtu.be/d39zxt6NWNk", "23.Recursion");
        youtubeVideosMap.put("https://youtu.be/aKYKYOQYjI0", "24.Recursion | Factorial Part 2");
        youtubeVideosMap.put("https://youtu.be/5rFOLyxjmOg", "25.Tail Recursion");
        youtubeVideosMap.put("https://youtu.be/6RIJrnZI7jk", "26.Constructor");
        youtubeVideosMap.put("https://youtu.be/HZLr7XDW7ak", "27.Secondary Constructor");
        youtubeVideosMap.put("https://youtu.be/ssAt_qrQpi0", "28.Inheritance");
        youtubeVideosMap.put("https://youtu.be/XR3XueD8_yM", "29.Constructor in Inheritance");

        youtubeVideosMap.put("https://youtu.be/kvpvem7BLq0", "30.Abstract Class");
        youtubeVideosMap.put("https://youtu.be/jpp6yAEPB0Y", "31.Interface");
        youtubeVideosMap.put("https://youtu.be/dV5P8JVfiIc", "32.Data Class");
        youtubeVideosMap.put("https://youtu.be/kH4pSAFSheU", "33.Object Keyword");
        youtubeVideosMap.put("https://youtu.be/o1bty2jSHpg", "34.Anonymous Inner Class");
        youtubeVideosMap.put("https://youtu.be/Xiqfy0OpZnc", "35.Companion Object");
        youtubeVideosMap.put("https://youtu.be/HTGj9ZxfiSA", "36.Companion Object | Factory Pattern");
        youtubeVideosMap.put("https://youtu.be/akiCZOVkQ_w", "37.Backtick as Escape Character");
        youtubeVideosMap.put("https://youtu.be/UTEYWN64m-s", "38.User Input in Kotlin");
        youtubeVideosMap.put("https://youtu.be/Mq4AbdNsFVw", "39.Array");
        youtubeVideosMap.put("https://youtu.be/N53A_bdbBXE", "40.List");
        youtubeVideosMap.put("https://youtu.be/dSsETZv9_8k", "41.List of Objects");
        youtubeVideosMap.put("https://youtu.be/ESm5AX1ZbSg", "42.Higher order function");
        youtubeVideosMap.put("https://youtu.be/MQTTkEsSKbI", "43.Filter and Map");





        YoutubeRecyclerAdapter youtubeRecyclerAdapter = new YoutubeRecyclerAdapter(getActivity(), youtubeVideosMap);
        mRecyclerView.setAdapter(youtubeRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addOnScrollListener(new FabScrollListener(getActivity()));

    }


}
