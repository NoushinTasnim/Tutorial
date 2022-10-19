package com.example.tutorial;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    ArrayList arrayList = new ArrayList<>();
    ArrayList arrayList2 = new ArrayList<>();
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        swipeRefreshLayout = findViewById(R.id.swipeContainer);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                int si = arrayList.size();
                arrayList.clear();
                arrayList2.clear();
                customAdapter.notifyItemRangeRemoved(0, si);
                PlayOn();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        swipeRefreshLayout.setRefreshing(true);
        PlayOn();
        swipeRefreshLayout.setRefreshing(false);
    }

    private void PlayOn() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://zenquotes.io/api/quotes";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
               JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(response);
                    JSONObject jsonObject;

                    int s = jsonArray.length();
                    for(int n = 0; n < s; n++)
                    {
                        jsonObject = jsonArray.getJSONObject(n);
                        arrayList.add(jsonObject.getString("q"));
                        arrayList2.add("-" +(jsonObject.getString("a")));
                    }
                    customAdapter = new CustomAdapter(RecyclerViewActivity.this, arrayList, arrayList2);
                    recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
                    customAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RecyclerViewActivity.this, "Could not fetch data. Restart App " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }
}