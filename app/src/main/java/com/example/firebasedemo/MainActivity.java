package com.example.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements ValueEventListener {
    Place city = new Place("Sochi", 80, 70);
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // получаем ссылку на облачную БД
        dbRef = FirebaseDatabase.getInstance().getReference();
        dbRef.child("myplace").addValueEventListener(this); // следим за изменением данных
        changePlace(city);


    }

    public void changePlace(Place p) {
        dbRef.child("myplace").setValue(p);
        //dbRef.child("myplace").child("anotherplace").setValue(p);


    }


    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        Place place = snapshot.getValue(Place.class);
        
        Log.d("mytag", "place: " + place);
        /*
       for (DataSnapshot s: snapshot.getChildren() ) {
           Log.d("mytag", "key: " + s.getKey());
           Place place = s.getValue(Place.class);
           Log.d("mytag", "place: " + place);
       }

         */

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}