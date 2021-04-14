package com.nirajgupta.musicdirectory;

import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Collections;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<SongData> data= Collections.emptyList();
    SongData current;
    int currentPos=0;

    // create constructor to innitilize context and data sent from MainActivity
    public SongAdapter(Context context, List<SongData> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.items, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        SongData current=data.get(position);
        myHolder.textTitle.setText(current.title);
        myHolder.textDescription.setText(current.description);

    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{

        TextView textTitle;
        TextView textDescription;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            textTitle= (TextView) itemView.findViewById(R.id.item_title);
            textDescription = (TextView) itemView.findViewById(R.id.item_description);
        }

    }

}