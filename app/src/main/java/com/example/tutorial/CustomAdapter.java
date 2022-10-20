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
    ArrayList<String> quotes;
    ArrayList<String> authors;
    Context context;

    public CustomAdapter(Context context, ArrayList<String> quotes, ArrayList<String> authors) {
        this.context = context;
        this.quotes = quotes;
        this.authors = authors;

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
        holder.quote.setText(quotes.get(position));
        holder.author.setText(authors.get(position));

        String p = quotes.get(position);

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
        return quotes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView quote, author;// init the item view's

        public MyViewHolder(View itemView) {
            super(itemView);

            Log.i("gt","as");
            // get the reference of item view's
            quote = (TextView) itemView.findViewById(R.id.quote);
            author = (TextView) itemView.findViewById(R.id.author);
        }
    }

    public void removeItem(int position) {
        quotes.remove(position);
        authors.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(String item, String item2, int position) {
        quotes.add(position, item);
        authors.add(position,item2);
        notifyItemInserted(position);
    }

    public ArrayList<String> getQuotes() {
        return quotes;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }
}

