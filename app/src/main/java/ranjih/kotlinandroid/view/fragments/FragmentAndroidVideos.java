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

public class FragmentAndroidVideos extends BaseFragment {
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
        youtubeVideosMap.put("https://www.youtube.com/watch?v=PqlbxoeeiOo&index=4&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "1. Hello World - Android");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=4-CRPx7zmMo&index=9&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "2. Activity Life cycle Part 1");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=jx0i36cqXXc&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa&index=10", "3. Activity Life cycle Part 2");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=w687Uq8yoQ4&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa&index=11", "4. Activity Life cycle Part 3");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=omDym65bdqM&index=12&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "5. Activity Life cycle Part 4");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=MtmHURWKCmg&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa&index=15", "6. Android Button onClick");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=2TK6KX_RDe8&index=23&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "7. Explicit Intents - Part 1");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=qurvm-E9AiU&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa&index=24", "8. Explicit Intents - Part 2");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=rqZnn5cf2As&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa&index=25", "9. Implicit Intents - Part 1");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=2FNGXp8Lzlw&index=26&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "10. Implicit Intents - Part 2");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=sVx46awjtFQ&index=32&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "11. Android Toasts");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=70-JVroY1Ng&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa&index=36", "12. Linear Layouts");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=rMksRBvYG28&index=37&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "13. Layout Weights");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=C56druJtByI&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa&index=38", "14. Android layout gravity");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=tn0P5zf4zfQ&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa&index=39", "15. Android gravity");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=7ivEwRxAsJw&index=50&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "16. Android Padding vs Margin");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=YJd_zZKzKx8&index=51&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "17. Relative Layout - Part 1");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=EWUrl5h-VA4&index=52&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "18. Relative Layout - Part 2");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=A4ZioB3Oz8A&index=53&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "19. Relative Layout - Part 3");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=iS0t2Suo_JM&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa&index=54", "20. Relative Layout - Part 4");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=Cp_ASAkzzVo&index=63&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "21. Table Layout");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=KZiP73VHSwY&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa&index=64", "22. Table layout Stretch, Shrink columns");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=TaCW4uL4P6c&index=66&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "23. Grid Layout");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=VDZas1ax_Xo&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa&index=73", "24. Edittext validation with TextWatcher");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=nPbkW6Us98w&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa&index=74", "25. Checkbox in Android");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=DHFFgqmWM9s&index=75&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "26. Android Toggle Button");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=uic3TVp_j3M&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa&index=76", "27. What is Android adapter?");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=BSZLqBWKTHw&index=77&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "28. Android Listview - Part 1");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=JEZpcpKOaZo&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa&index=78", "29. Android Listview - Part 2");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=fxVeFwtIpVc&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa&index=81", "30. LayoutInflater - Part 1");

        youtubeVideosMap.put("https://www.youtube.com/watch?v=1Y0LlmTCOkM&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa&index=82", "31. LayoutInflater - Part 2");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=ka5Tk7J9rG0&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa&index=93", "32. Custom Listview With BaseAdapter");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=VEgw3Ia6PIs&index=104&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "33. Android Spinner");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=6GyGtCMoR_U&index=111&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "34. Fragments In Android");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=vk7VKUFOlbY&index=112&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "35. Fragment Lifecycle - Part 1");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=HdpmeT4Q1hk&index=113&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "36. Fragment Lifecycle - Part 2");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=kxgU2hhWToU&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa&index=143", "37. Navigation Drawer");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=0BUljVIgnoE&index=159&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "38. Android SQLite Tutorial");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=tsNgtAdlof8&index=160&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "39. Android SQLite Database Schema Part 1");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=ge7m4nWmggs&index=161&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "40. Android SQLite Database Schema Part 2");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=-7yDXaW6C9U&index=162&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "41. Android SQLite Insert Tutorial Part 1");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=0JNP7El2kHs&index=163&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "42. SQLite Database Insert Statement Part 2");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=Npc8gQMiXiI&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa&index=164", "43. SQLite Query SELECT Part 1");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=aOpUmtmG0o0&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa&index=165", "44. Android SQLite INSERT Part 2");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=MDdkdsG-Yww&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa&index=166", "45. Android SQLite SELECT query Part 3");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=HioR0tIfuY8&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa&index=167", "46. Android SQLite UPDATE and DELETE Part 1");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=pzrcC97rjKI&index=168&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "47. Android SQLite UPDATE and DELETE Part 2");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=IcIFJ5V3Ibg&index=169&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "48. What is a process?");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=A0PAhoHzlsQ&index=172&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "49. Android Multithreading Part 1");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=lznss-0gEHU&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa&index=173", "50. Android Multithreading Part 2");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=8KceLnd-gBc&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa&index=174", "51. Android Multithreading Example Part 1");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=g-NqhejbQsQ&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa&index=175", "52. Android Multithreading Example Part 2");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=iTBnuCYeq3E&index=176&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "53. Android Multithreading Example Part 3");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=I95Iuc8nsUw&index=177&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "54. Android Multithreading Example Part 4");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=LJ_pUlWzGsc&index=179&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "55. Android Handler Tutorial");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=gm5cMa5wA1g&index=181&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "56. Android Handler Example Post Runnable Messages");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=wYDJH6tDyNg&index=182&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "57. Android Looper Example");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=V4q0sTIntsk&index=183&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "58. Android AsyncTask Tutorial");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=dVwR5Gpw1_E&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa&index=184", "59. Android AsyncTask Example");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=GDkL2uEn8cg&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa&index=201", "60. What is Dependency Injection in Android?");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=1A4LY8gUEDs&index=202&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "61. Android Butterknife Tutorial");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=lhBfCC6_PwQ&index=203&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "62. Android Roboguice Tutorial");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=SKFB8u0-VA0&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa&index=205", "63. Android Dagger 2 Tutorial");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=r_AZ4VGQSq8&index=204&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "64. Android Annotations Tutorial");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=xPGneeYlq08&index=208&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "65. Android Glide Example");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=x_5Ifs8kIrI&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa&index=209", "66. Android Realm Tutorial");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=aZq099t-fJk&index=210&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "67. Introduction To The RecyclerView");
        youtubeVideosMap.put("https://www.youtube.com/watch?v=LqIAg7EOkh0&index=212&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa", "68. Android Studio Live Templates");





        YoutubeRecyclerAdapter youtubeRecyclerAdapter = new YoutubeRecyclerAdapter(getActivity(), youtubeVideosMap);
        mRecyclerView.setAdapter(youtubeRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addOnScrollListener(new FabScrollListener(getActivity()));

    }


}
