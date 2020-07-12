package com.example.helpingpaws.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helpingpaws.Interface.ItemClickListener;
import com.example.helpingpaws.R;

public class PetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView pet_name;
    public ImageView pet_image;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public PetViewHolder(@NonNull View itemView) {
        super(itemView);

        pet_name=(TextView)itemView.findViewById(R.id.pet_name);
        pet_image=(ImageView)itemView.findViewById(R.id.pet_image);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}
