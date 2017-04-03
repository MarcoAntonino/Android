package com.example.antoninom.test03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent i=getIntent();
        String message=i.getStringExtra("result");
        TextView tv=(TextView) findViewById(R.id.textView);
        tv.setText(message);
    }
}
