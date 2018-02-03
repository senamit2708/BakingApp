package com.example.senamit.bakingapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by senamit on 2/2/18.
 */

public class BakingRecipeStepAdapter extends RecyclerView.Adapter<BakingRecipeStepAdapter.ViewHolder>{

    Context context;
    List<BakingItems> bakingItems;
    ListItemStepClickListener listItemStepClickListener;
    public static final String LOG_TAG = BakingRecipeStepAdapter.class.getSimpleName();

    public BakingRecipeStepAdapter(List<BakingItems> bakingItems, ListItemStepClickListener listItemStepClickListener) {
        this.bakingItems = bakingItems;
        this.listItemStepClickListener = listItemStepClickListener;
    }

    public BakingRecipeStepAdapter(List<BakingItems> bakingItems) {
        Log.i(LOG_TAG, "inside the constructor of step adapter");
        this.bakingItems = bakingItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_recipe_step_description, parent, false);
        context = parent.getContext();
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        String shortDescription = bakingItems.get(position).getShortDiscription();
        Log.i(LOG_TAG, "the shortdescription in adapter is "+shortDescription);
        holder.txtRecipeStepShortDescription.setText(shortDescription);
        String logdescription = holder.txtRecipeStepShortDescription.getText().toString();
        Log.i(LOG_TAG, "the recipe description is "+ logdescription);
    }

    @Override
    public int getItemCount() {
        Log.i(LOG_TAG, "the size of araay is in adapter is "+bakingItems.size());
        return bakingItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtRecipeStepShortDescription;


        public ViewHolder(View itemView) {
            super(itemView);
            txtRecipeStepShortDescription = itemView.findViewById(R.id.txt_recipe_step_shortDescription);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.i(LOG_TAG, "inside the onclick of viewholder");
            int clickItemPosition = getAdapterPosition();
            listItemStepClickListener.onListItemStepClick(clickItemPosition, bakingItems.get(clickItemPosition));
        }
    }

    public interface ListItemStepClickListener{
        void onListItemStepClick(int clickItemIndex, BakingItems bakingItems);
    }

}
