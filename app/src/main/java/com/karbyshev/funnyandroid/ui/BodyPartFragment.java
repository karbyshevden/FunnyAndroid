package com.karbyshev.funnyandroid.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.karbyshev.funnyandroid.R;
import com.karbyshev.funnyandroid.data.AndroidImageAssets;

import java.util.ArrayList;
import java.util.List;

public class BodyPartFragment extends Fragment {
    public static final String IMAGE_ID_LIST = "image_ids";
    public static final String LIST_INDEX = "list_index";

    private List<Integer> mImageIds;
    private int mListIndex;

    public BodyPartFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null){
            mImageIds = savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST);
            mListIndex = savedInstanceState.getInt(LIST_INDEX);
        }

        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        final ImageView imageView = (ImageView)rootView.findViewById(R.id.body_part_imageView);

        if (mImageIds != null){
            imageView.setImageResource(mImageIds.get(mListIndex));

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListIndex < mImageIds.size()-1){
                        mListIndex++;
                    } else {
                        mListIndex = 0;
                    }

                    imageView.setImageResource(mImageIds.get(mListIndex));
                }
            });
        }

        return rootView;
    }

    public void setmImageIds(List<Integer> mImageIds) {
        this.mImageIds = mImageIds;
    }

    public void setmListIndex(int mListIndex) {
        this.mListIndex = mListIndex;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>) mImageIds);
        outState.putInt(LIST_INDEX, mListIndex);
    }
}
