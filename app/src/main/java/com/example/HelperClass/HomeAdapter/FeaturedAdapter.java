package com.example.HelperClass.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.loginregister.R;

import java.util.ArrayList;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.featuredViewHolder> {

    ArrayList<FeaturedHelperClass> featuredlocations;

    public FeaturedAdapter(ArrayList<FeaturedHelperClass> featuredlocations) {
        this.featuredlocations = featuredlocations;
    }

    @NonNull
    @Override
    public featuredViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_design,parent,false);
        featuredViewHolder fvh =new featuredViewHolder(view);

        return fvh;
    }

    @Override
    public void onBindViewHolder(@NonNull featuredViewHolder holder, int position) {
        FeaturedHelperClass featuredHelperClass = featuredlocations.get(position);
        holder.img_view.setImageResource(featuredHelperClass.getImage());
    }

    @Override
    public int getItemCount() {
        return featuredlocations.size();
    }

    public static class featuredViewHolder extends RecyclerView.ViewHolder {
        ImageView img_view;
        public featuredViewHolder(@NonNull View itemView) {
            super(itemView);
            //hooks
            img_view = itemView.findViewById(R.id.featured_img);

        }
    }
}
