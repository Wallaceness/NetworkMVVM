package com.example.networkmvvm.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.networkmvvm.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ItemHolder> {
    private ArrayList<String> urls;
    private LayoutInflater theInflater;

    RecycleAdapter(ArrayList<String> urls, Context context){
        this.urls=urls;
        theInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View theHolder= theInflater.inflate(R.layout.list_item, parent, false);
        return new ItemHolder(theHolder, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Picasso.get().load(urls.get(position)).into(holder.urlItem);
    }


    @Override
    public int getItemCount() {
        return urls.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder{
        ImageView urlItem;
        final RecycleAdapter theAdapter;

        public ItemHolder(View itemView, RecycleAdapter mainAdapter){
            super(itemView);
            urlItem= itemView.findViewById(R.id.urlItem);
            this.theAdapter= mainAdapter;
        }
    }
}
