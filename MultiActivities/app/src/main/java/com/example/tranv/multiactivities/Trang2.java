package com.example.tranv.multiactivities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class Trang2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang2);

        Button btnAzusa = (Button) findViewById(R.id.btnAzusa);
        Button btnYui = (Button) findViewById(R.id.btnYui);


        if (btnAzusa != null) {
            btnAzusa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   ChuyenHinhAnh("Azusa");
                }
            });
        }
        if (btnYui != null) {
            btnYui.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ChuyenHinhAnh("Yui");
                }
            });
        }
    }

    public void ChuyenHinhAnh(String charname){
        final Fragment frag = new FragmentImageContainer();

        Bundle bundle = new Bundle();
        bundle.putString("charName", charname);
        frag.setArguments(bundle);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        fragmentTransaction.replace(R.id.flImage, frag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }

//    @Override
//    public void onBackPressed() {
//        WebView mWebView = new WebView(this);
//        if (mWebView.canGoBack()) {
//            mWebView.goBack();
//            return;
//        }
//
//        // Otherwise defer to system default behavior.
//        super.onBackPressed();
//    }
}

