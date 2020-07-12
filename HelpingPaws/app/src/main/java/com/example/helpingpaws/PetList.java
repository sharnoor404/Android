package com.example.helpingpaws;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.helpingpaws.Model.Pets;
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
    FirebaseRecyclerAdapter<Pets, PetViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list);

        //firebase
        database=FirebaseDatabase.getInstance();
        petList=database.getReference("Pets");
        recyclerView=(RecyclerView)findViewById(R.id.recycler_pets);
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
        adapter=new FirebaseRecyclerAdapter<Pets, PetViewHolder>(Pets.class,
                R.layout.pet_option,
                PetViewHolder.class, petList.orderByChild("MenuId").equalTo(categoryId)) {
            @Override
            protected void populateViewHolder(PetViewHolder petViewHolder, Pets pets, int i) {
                petViewHolder.pet_name.setText(pets.getName());
                Picasso.with(getBaseContext()).load(pets.getImage())
                        .into(petViewHolder.pet_image);
            }
        };
    }
}
