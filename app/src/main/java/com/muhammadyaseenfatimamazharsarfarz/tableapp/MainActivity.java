package com.muhammadyaseenfatimamazharsarfarz.tableapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {
    EditText enter_no_field;
    Button submit_btn;
    ListView list_view;
    ArrayList<String> list;
    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adView = findViewById(R.id.adView);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        enter_no_field = findViewById(R.id.enter_no_field);
        submit_btn = findViewById(R.id.submit_btn);
        list_view = findViewById(R.id.list_view);
        list = new ArrayList<>();

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (enter_no_field.getText().toString().trim().isEmpty()) {
                    Toasty.error(MainActivity.this, "Please enter a number", Toast.LENGTH_LONG).show();
                }
                else {
                    int getNo = Integer.parseInt(enter_no_field.getText().toString().trim());

                    if (!list.isEmpty()) {
                        list.clear();

                    }
                    printTable(getNo);

                }
            }
        });
    }

    private void printTable(long no) {
        for (int i = 1; i <= 10; i++) {
            String result = no + " x " + i + " = " + no * i;
            Log.d("result", result);
            list.add(result);
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, list);
            list_view.setAdapter(adapter);
        }
    }
}