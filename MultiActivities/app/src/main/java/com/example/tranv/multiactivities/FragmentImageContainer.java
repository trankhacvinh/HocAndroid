package com.example.tranv.multiactivities;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by tranv on 4/1/2016.
 */
public class FragmentImageContainer extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // inflate(@LayoutRes int resource, @Nullable ViewGroup root, boolean attachToRoot)
        View view= inflater.inflate(R.layout.fragment_image_container, container, false);

        ImageView imageContainer = (ImageView) view.findViewById(R.id.imgImageContainer);
        Bundle bundle = this.getArguments();
        String charName = bundle.getString("charName");
        if (charName.equals("Azusa")) {
            imageContainer.setImageResource(R.mipmap.azusa);
        }
        else {
            imageContainer.setImageResource(R.mipmap.yui);
        }
        return view;
    }
}
