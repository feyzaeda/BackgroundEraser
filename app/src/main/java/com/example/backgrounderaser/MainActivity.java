package com.example.backgrounderaser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyCustomView myCustomView = new MyCustomView(MainActivity.this);

        RelativeLayout rootLayout = findViewById(R.id.activity_main);
        rootLayout.addView(myCustomView);
        
    }
}