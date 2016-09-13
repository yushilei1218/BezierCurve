package com.yushilei.beziercurve;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void getBezierPoint(View view) {
        startActivity(new Intent(this, GetBezierPointActivity.class));
    }

    public void parbola(View view) {
        startActivity(new Intent(this, ParbolaActivity.class));
    }
}
