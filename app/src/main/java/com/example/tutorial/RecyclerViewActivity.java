package com.example.tutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    ArrayList arrayList = new ArrayList();
    ArrayList arrayList2 = new ArrayList();
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        swipeRefreshLayout = findViewById(R.id.swipeContainer);

        arrayList.add("Mr. X");
        arrayList.add("Mrs. X");
        arrayList.add("Mr. Y");
        arrayList.add("Mr. Q");
        arrayList.add("Mrs. L");
        arrayList.add("Mrs. E");
        arrayList.add("Mr. A");
        arrayList.add("Mrs. Z");

        arrayList2.add("Lawyer");
        arrayList2.add("Realtor");
        arrayList2.add("Car Dealer");
        arrayList2.add("Painter");
        arrayList2.add("Baker");
        arrayList2.add("Fashion Designer");
        arrayList2.add("Manager");
        arrayList2.add("Scientist");

        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        customAdapter = new CustomAdapter(this, arrayList, arrayList2);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
    }
}