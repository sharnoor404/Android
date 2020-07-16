package com.example.helpingpaws;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.helpingpaws.Model.Pet;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class PetDetail extends AppCompatActivity {

    TextView pet_name,pet_price,pet_description;
    ImageView pet_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;

    String petId="";

    FirebaseDatabase database;
    DatabaseReference pets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_detail);

        //Firebase
        database= FirebaseDatabase.getInstance();
        pets=database.getReference("Pets");

        //Init view
        numberButton=(ElegantNumberButton)findViewById(R.id.number_button);
        btnCart=(FloatingActionButton)findViewById(R.id.btnCart);

        pet_description=(TextView)findViewById(R.id.pet_description);
        pet_name=(TextView)findViewById(R.id.pet_name);
        pet_price=(TextView)findViewById(R.id.pet_price);

        pet_image=(ImageView)findViewById(R.id.img_pet);

        collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        //Get Pet Id from Intent
        if(getIntent()!=null)
            petId=getIntent().getStringExtra("PetId");
        if(!petId.isEmpty())
        {
            getDetailPet(petId);
        }


    }

    private void getDetailPet(String petId) {
        pets.child(petId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Pet pet=snapshot.getValue(Pet.class);
                //set Image
                Picasso.with(getBaseContext()).load(pet.getImage())
                        .into(pet_image);
                collapsingToolbarLayout.setTitle(pet.getName());
                pet_price.setText(pet.getPrice());
                pet_name.setText(pet.getName());

                pet_description.setText(pet.getDescription());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
