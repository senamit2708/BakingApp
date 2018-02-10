package com.example.senamit.bakingapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by senamit on 23/1/18.
 */

public class BakingRecipeNameAdapter extends RecyclerView.Adapter<BakingRecipeNameAdapter.ViewHolder> {

    Context context;
    List<BakingItems> bakingItems;
    ListItemClickListener onClickListener;
    private static final String LOG_TAG = BakingRecipeNameAdapter.class.getSimpleName();

    public BakingRecipeNameAdapter(List<BakingItems> bakingItems, ListItemClickListener onClickListener) {
        this.bakingItems = bakingItems;
        this.onClickListener = onClickListener;
    }

    public BakingRecipeNameAdapter(List<BakingItems> bakingItems) {
        this.bakingItems = bakingItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.baking_recipe_name_cardview, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String url = bakingItems.get(position).getRecipeImage();
        holder.cardTxtRecipeName.setText(bakingItems.get(position).getRecipeName());
        Picasso.with(context).load(url).placeholder(R.mipmap.cookies2).error(R.mipmap.cookies2).into(holder.cardImageView);
    }

    @Override
    public int getItemCount() {
        return bakingItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView cardTxtRecipeName;
        ImageView cardImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardImageView = itemView.findViewById(R.id.cardImageView);
            cardTxtRecipeName = itemView.findViewById(R.id.cardTxtRecipeName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedItemIndex = getAdapterPosition();
            onClickListener.onListItemClick(clickedItemIndex, bakingItems.get(clickedItemIndex));
        }
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex, BakingItems bakingItems);
    }
}
