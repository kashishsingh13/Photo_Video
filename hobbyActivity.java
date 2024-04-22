package com.example.crudsqlite.xmlAndjsonparsing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.crudsqlite.R;

import java.util.ArrayList;

public class hobbyActivity extends AppCompatActivity {
RecyclerView rv_hobby;
HobbyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobby);

        rv_hobby =findViewById(R.id.rv_hobby);

        rv_hobby.setLayoutManager(new LinearLayoutManager(this));


        ArrayList<String> hobbies = getIntent().getStringArrayListExtra("hobbies");
        adapter =new HobbyAdapter(this,hobbies);
        rv_hobby.setAdapter(adapter);


    }

}