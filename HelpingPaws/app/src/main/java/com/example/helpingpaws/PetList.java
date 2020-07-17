package com.example.helpingpaws;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.helpingpaws.Interface.ItemClickListener;
import com.example.helpingpaws.Model.Pet;
import com.example.helpingpaws.ViewHolder.PetViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class PetList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference petList;
    String categoryId="";
    FirebaseRecyclerAdapter<Pet, PetViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list);

        //firebase
        database=FirebaseDatabase.getInstance();
        petList=database.getReference("Pets");

        recyclerView=(RecyclerView)findViewById(R.id.recycler_pet);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Get intent here
        if(getIntent()!=null)
        {
            categoryId=getIntent().getStringExtra("CategoryId");
        }
        if(!categoryId.isEmpty() &&  categoryId!=null)
        {
            loadListPet(categoryId);
        }

    }

    private void loadListPet(String categoryId) {
        adapter=new FirebaseRecyclerAdapter<Pet, PetViewHolder>(Pet.class,
                R.layout.pet_option,
                PetViewHolder.class,
                petList.orderByChild("PetID").equalTo(categoryId)) {
            @Override
            protected void populateViewHolder(PetViewHolder petViewHolder, Pet pets, int i) {
                petViewHolder.pet_name.setText(pets.getName());
                Picasso.with(getBaseContext()).load(pets.getImage())
                        .into(petViewHolder.pet_image);

                final Pet local=pets;


                petViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //Start new activity
                        Intent petDetail=new Intent(PetList.this,PetDetail.class);
                        petDetail.putExtra("PetID",adapter.getRef(position).getKey());
                        startActivity(petDetail);
                    }
                });
            }
        };

        //set adapter
        recyclerView.setAdapter(adapter);
    }
}
