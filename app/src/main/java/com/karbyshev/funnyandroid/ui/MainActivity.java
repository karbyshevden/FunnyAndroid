package com.karbyshev.funnyandroid.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.karbyshev.funnyandroid.R;
import com.karbyshev.funnyandroid.data.AndroidImageAssets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        BodyPartFragment headFragment = new BodyPartFragment();
        headFragment.setmImageIds(AndroidImageAssets.getHeads());
        fragmentManager.beginTransaction().add(R.id.head_container, headFragment).commit();

        BodyPartFragment bodyFragment = new BodyPartFragment();
        bodyFragment.setmImageIds(AndroidImageAssets.getBodies());
        fragmentManager.beginTransaction().add(R.id.body_container, bodyFragment).commit();

        BodyPartFragment legFragment = new BodyPartFragment();
        legFragment.setmImageIds(AndroidImageAssets.getLegs());
        fragmentManager.beginTransaction().add(R.id.leg_container, legFragment).commit();
    }
}
