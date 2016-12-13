package com.leelorz.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    int sum = 0;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        sum = intent.getIntExtra("result", 0);

        initInstances();
    }

    private void initInstances() {
        tvResult = (TextView) findViewById(R.id.tvResult);
        tvResult.setText("Result = " + sum);
    }
}
