package com.example.jiabo.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

   // private TextView tv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO: finish the code
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv = (TextView) findViewById(R.id.tv);

        tv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.i("tv width == ", tv.getWidth()+"");
            }
        });
        //tv.getViewTreeObserver().addOnGlobalLayoutListener();
//        for (int counter = 0; counter < 10; counter++) {
//            Log.i("MainActivity", "value: "+ counter);
//        }
//        Log.i("", "");
        
    }
}
