package com.example.tranv.multiactivities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Trang3 extends AppCompatActivity {

    private String charName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang3);

        Intent intent = getIntent();
        String message = intent.getStringExtra("message");

        TextView textViewMessage = (TextView) findViewById(R.id.textViewMessage);
        textViewMessage.setText(message);


        final SharedData sharedData = new SharedData();
        sharedData.sharedPreferences = getSharedPreferences("SharedMessage", MODE_PRIVATE);
        String sharedMessage = sharedData.loadData(sharedData.sharedPreferences);
        Toast.makeText(getApplicationContext(), sharedMessage, Toast.LENGTH_SHORT).show();
        sharedData.clearData(sharedData.sharedPreferences);

        Button btnSwitchChar = (Button) findViewById(R.id.btnSwitchChar);
        if (btnSwitchChar != null) {
            btnSwitchChar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment frag;
                    if (!charName.equals("Azusa")) {
                        frag = new Trang3_bottom();
                        charName = "Azusa";
                    } else {
                        frag = new Trang4_image();
                        charName = "Yui";
                    }
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    fragmentTransaction.replace(R.id.flContent, frag);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                }
            });
        }
    }
}
