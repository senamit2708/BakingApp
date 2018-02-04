package com.example.senamit.bakingapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.senamit.bakingapp.R;

/**
 * Created by senamit on 3/2/18.
 */

public class FragmentRecipeBakingProcess extends Fragment{

    private static final String LOG_TAG = FragmentRecipeBakingProcess.class.getSimpleName();
    Context context;
    TextView text2;
    String clickItemIndex;



    public FragmentRecipeBakingProcess() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_baking_process, container, false);
        text2= rootView.findViewById(R.id.text2);

        text2.setText(clickItemIndex);

        return  rootView;
    }


    public void setClickItemIndex(String clickItemIndex) {
        this.clickItemIndex = clickItemIndex;
    }
}
