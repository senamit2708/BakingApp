package com.example.senamit.bakingapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.senamit.bakingapp.R;

/**
 * Created by senamit on 31/1/18.
 */

public class FragmentRecipeSteps extends Fragment {

    public static final String LOG_TAG = FragmentRecipeSteps.class.getSimpleName();

    public FragmentRecipeSteps() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recipe_step, container, false);
        return  rootView;
    }
}
