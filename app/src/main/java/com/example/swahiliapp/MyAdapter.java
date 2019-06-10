package com.example.swahiliapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    //create the adapter to populate the recycler view with the words
    private String[] dataSet = new String[]{};

    void setDataSet(String[] dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        TextView textView = (TextView)
                LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.words,viewGroup,false);
        return new MyViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (!dataSet[i].isEmpty()){
        myViewHolder.textView.setText((i+1)+". "+dataSet[i]);}
    }

    @Override
    public int getItemCount() {
        return dataSet.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        MyViewHolder(@NonNull TextView textView) {
            super(textView);
            this.textView = textView;
        }
    }
}
