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
    Place city = new Place("Beijing", 80, 70);
    DatabaseReference dbRef;
    final String CHILD = "myplace123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // получаем ссылку на облачную БД
        dbRef = FirebaseDatabase.getInstance().getReference();
        dbRef.child(CHILD).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("mytag", "count: " + snapshot.getChildrenCount());
                for (DataSnapshot s: snapshot.getChildren()) {
                    Place p = s.getValue(Place.class);
                    Log.d("mytag", "place: " + p);
                }
                
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("mytag", "error");
            }
        });
        changePlace(city);
        Log.d("mytag", "city changed");

        // TODO: добавить отображение данных


    }

    public void changePlace(Place p) {
        dbRef.child(CHILD).setValue(p);
        dbRef.child(CHILD).push().setValue(new Place("SPb", 11, 22));
        dbRef.child(CHILD).push().setValue(new Place("Moscow", 33, 44));
        //dbRef.child("myplace").
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