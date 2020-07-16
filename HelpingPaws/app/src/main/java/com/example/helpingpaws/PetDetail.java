package com.example.helpingpaws;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

        pet_image=(ImageView)findViewById(R.id.pet_image);

        collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapsing);

    }
}
