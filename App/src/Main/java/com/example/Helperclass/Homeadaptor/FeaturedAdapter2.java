package com.example.HelperClass.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginregister.R;

import java.util.ArrayList;

public class FeaturedAdapter2 extends RecyclerView.Adapter<FeaturedAdapter2.FeaturedViewHolder> {

    ArrayList<FeaturedHelperClass2> featuredloc;

    public FeaturedAdapter2(ArrayList<FeaturedHelperClass2> featuredloc) {
        this.featuredloc = featuredloc;
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_design2,parent,false);
         FeaturedViewHolder featuredViewHolder=new FeaturedViewHolder(view);
         return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {
        FeaturedHelperClass2 featuredHelperClass2 = featuredloc.get(position);
        holder.imageView.setImageResource(featuredHelperClass2.getImage());
        holder.textView.setText(featuredHelperClass2.getText());
    }

    @Override
    public int getItemCount() {
        return featuredloc.size();
    }

    public static class  FeaturedViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView= itemView.findViewById(R.id.featured2_img);
            textView=itemView.findViewById(R.id.featured2_txt);

        }
    }
}
