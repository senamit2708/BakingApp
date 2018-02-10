package com.example.senamit.bakingapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.senamit.bakingapp.BakingItems;
import com.example.senamit.bakingapp.BakingRecipeStepAdapter;
import com.example.senamit.bakingapp.FragmentRecipeIngredientLoader;
import com.example.senamit.bakingapp.R;
import com.example.senamit.bakingapp.fragmentRecipeStepLoader;

import java.util.List;

/**
 * Created by senamit on 31/1/18.
 */

public class FragmentRecipeSteps extends Fragment {

    public static final String LOG_TAG = FragmentRecipeSteps.class.getSimpleName();
    Context context;
    TextView txtRecipeIngredient;
    TextView txtRecipeStep;
    int recipeId;
    private RecyclerView recyclerRecipeStep;
    private RecyclerView.LayoutManager mLayoutManager;
    BakingRecipeStepAdapter bakingRecipeStepAdapter;
    Bundle saveInstanceState;
    String stringUrl = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    StepSelectedListener stepSelectedListener;

    public FragmentRecipeSteps() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.saveInstanceState= savedInstanceState;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            stepSelectedListener = (StepSelectedListener)context;
        }
        catch (Exception e){
            Log.e(LOG_TAG, "the interface is not attached to the acitiviyt");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recipe_step, container, false);
        context=container.getContext();
         txtRecipeIngredient = (TextView)rootView.findViewById(R.id.txt_recipe_ingredients);
         txtRecipeStep=(TextView)rootView.findViewById(R.id.txt_recipe_Step);
         recyclerRecipeStep = rootView.findViewById(R.id.recyclerRecipeStep);
        mLayoutManager = new LinearLayoutManager(context);
        recyclerRecipeStep.setLayoutManager(mLayoutManager);

         RecipeStepClass recipeStepClass = new RecipeStepClass();
         recipeStepClass.loadercall();
         RecipeIngredientClass recipeIngredientClass = new RecipeIngredientClass();
         recipeIngredientClass.loadercall();

        return  rootView;
    }


    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public  class RecipeStepClass implements LoaderManager.LoaderCallbacks<List<BakingItems>>, BakingRecipeStepAdapter.ListItemStepClickListener{

        @Override
        public Loader<List<BakingItems>> onCreateLoader(int id, Bundle args) {
            return new fragmentRecipeStepLoader(context,stringUrl, recipeId );
        }

        @Override
        public void onLoadFinished(Loader<List<BakingItems>> loader, List<BakingItems> data) {
            bakingRecipeStepAdapter = new BakingRecipeStepAdapter(data, this);
            recyclerRecipeStep.setAdapter(bakingRecipeStepAdapter);
        }

        @Override
        public void onLoaderReset(Loader<List<BakingItems>> loader) {
            recyclerRecipeStep.setAdapter(null);
        }

        public  void loadercall() {
            getLoaderManager().initLoader(1, saveInstanceState, this);
        }

        @Override
        public void onListItemStepClick(int clickItemIndex, List<BakingItems> bakingItems) {
            stepSelectedListener.stepNumberSelected(clickItemIndex, bakingItems);
        }
    }

    public class RecipeIngredientClass implements LoaderManager.LoaderCallbacks<List<BakingItems>>{

        @Override
        public Loader<List<BakingItems>> onCreateLoader(int id, Bundle args) {
            return new FragmentRecipeIngredientLoader(context, recipeId,stringUrl );
        }

        @Override
        public void onLoadFinished(Loader<List<BakingItems>> loader, List<BakingItems> data) {

            String ingredient;
            int quantity;
            String measure;
            String displayIngredient = null;

            for (int i=0; i<data.size();i++){
                ingredient = data.get(i).getIngredient();
                quantity = data.get(i).getQuantity();
                measure = data.get(i).getMeasure();

                displayIngredient= quantity +", "+measure+", "+ingredient;

            }
            txtRecipeIngredient.setText(displayIngredient);


        }

        @Override
        public void onLoaderReset(Loader<List<BakingItems>> loader) {

        }

        public  void loadercall() {
            Log.i(LOG_TAG, "inside the inner class");
            getLoaderManager().initLoader(2, null, this);

        }
    }

    public interface StepSelectedListener{
         void stepNumberSelected(int clickItemIndex, List<BakingItems> bakingItems);
    }

}
