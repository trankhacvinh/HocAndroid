package com.example.tranv.multiactivities;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tranv on 4/1/2016.
 */
public class Trang4_image extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // inflate(@LayoutRes int resource, @Nullable ViewGroup root, boolean attachToRoot)
        View view= inflater.inflate(R.layout.trang4_image, container, false);
        return view;
    }
}
