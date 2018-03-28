package com.karbyshev.funnyandroid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.karbyshev.funnyandroid.R;
import com.karbyshev.funnyandroid.data.AndroidImageAssets;

public class FirstActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{

    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    private boolean mTwoPane;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        if (findViewById(R.id.android_me_linear_layout) != null){
            mTwoPane = true;

            Button nextButton = (Button)findViewById(R.id.next_button);
            nextButton.setVisibility(View.GONE);

            GridView gridView = (GridView)findViewById(R.id.images_grid_view);
            gridView.setNumColumns(2);

            if (savedInstanceState == null) {
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
        } else {
            mTwoPane = false;
        }
    }

    @Override
    public void onImageSelected(int position) {

        int bodyPartNumber = position /12;
        int listIndex = position - 12*bodyPartNumber;

        if (mTwoPane){
            BodyPartFragment newFragment = new BodyPartFragment();
            switch (bodyPartNumber){
                case 0:
                    newFragment.setmImageIds(AndroidImageAssets.getHeads());
                    newFragment.setmListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction().replace(R.id.head_container, newFragment).commit();
                    break;
                case 1:
                    newFragment.setmImageIds(AndroidImageAssets.getBodies());
                    newFragment.setmListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction().replace(R.id.body_container, newFragment).commit();
                    break;
                case 2:
                    newFragment.setmImageIds(AndroidImageAssets.getLegs());
                    newFragment.setmListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction().replace(R.id.leg_container, newFragment).commit();
                    break;
            }

        } else {
            switch (bodyPartNumber) {
                case 0:
                    headIndex = listIndex;
                    break;
                case 1:
                    bodyIndex = listIndex;
                    break;
                case 2:
                    legIndex = listIndex;
                    break;
                default:
                    break;
            }
        }

        Bundle b = new Bundle();
        b.putInt("headIndex", headIndex);
        b.putInt("bodyIndex", bodyIndex);
        b.putInt("legIndex", legIndex);

        final Intent intent = new Intent(this, MainActivity.class);
        intent.putExtras(b);

        Button button = (Button)findViewById(R.id.next_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}
