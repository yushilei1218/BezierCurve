package com.yushilei.beziercurve;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.yushilei.beziercurve.widget.ParabolaView;

import java.util.Random;

public class ParbolaActivity extends AppCompatActivity {

    private ParabolaView parbola;
    Random mRandom = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parbola);
        parbola = (ParabolaView) findViewById(R.id.parabola);

        int[] rids = new int[]{R.mipmap.heart1, R.mipmap.heart2, R.mipmap.heart3, R.mipmap.heart4,
                R.mipmap.heart5, R.mipmap.heart6, R.mipmap.heart7, R.mipmap.heart8,
                R.mipmap.heart9};

        for (int i = 0; i < 20; i++) {
            ImageView img = (ImageView) LayoutInflater.from(this).inflate(R.layout.img, parbola, false);
            int index = mRandom.nextInt(rids.length);
            img.setImageResource(rids[index]);
            parbola.addView(img);
        }
    }
}
