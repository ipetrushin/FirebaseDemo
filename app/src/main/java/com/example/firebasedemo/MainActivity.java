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
    Place city = new Place("Irkutsk", 103.6, 53.4);
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // получаем ссылку на облачную БД
        dbRef = FirebaseDatabase.getInstance().getReference();
        dbRef.child("myplace").addValueEventListener(this);
        changePlace(city);


    }

    public void changePlace(Place p) {
        dbRef.child("myplace").setValue(p);
        dbRef.child("myplace").push().setValue(p);

    }


    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
       Place place = snapshot.getValue(Place.class);
       Log.d("mytag", "key: " + snapshot.getKey());
       Log.d("mytag", "place: " + place);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}