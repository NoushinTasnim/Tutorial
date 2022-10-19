package com.example.tutorial;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    ArrayList<String> people;
    ArrayList<String> occupation;
    Context context;

    public CustomAdapter(Context context, ArrayList<String> people, ArrayList<String> occupation) {
        this.context = context;
        this.people = people;
        this.occupation = occupation;

        Log.i("here","nopes");
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        Log.i("er","assdf");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // set the data in items
        holder.person.setText(people.get(position));
        holder.job.setText(occupation.get(position));

        String p = people.get(position);

        Log.i("hey", p);

        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with person name on item click
                Toast.makeText(context, p, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView person, job;// init the item view's

        public MyViewHolder(View itemView) {
            super(itemView);

            Log.i("gt","as");
            // get the reference of item view's
            person = (TextView) itemView.findViewById(R.id.quote);
            job = (TextView) itemView.findViewById(R.id.author);
        }
    }
}

