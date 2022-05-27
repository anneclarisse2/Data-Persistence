package com.example.bookstore;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<BookModal> bookModalArrayList;
    private Context context;

    // constructor
    public BookAdapter(ArrayList<BookModal> bookModalArrayList, Context context) {
        this.bookModalArrayList = bookModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        BookModal modal = bookModalArrayList.get(position);
        holder.bookTitleTV.setText(modal.getbookTitle());
        holder.bookYearTV.setText(modal.getbookYear());
        holder.bookAuthorTV.setText(modal.getbookAuthor());


        // below line is to add on click listener for our recycler view item.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // on below line we are calling an intent.
                Intent i = new Intent(context, UpdateBookActivity.class);
                // below we are passing all our values.
                i.putExtra("title", modal.getbookTitle());
                i.putExtra("year", modal.getbookYear());
                i.putExtra("author", modal.getbookAuthor());
                // starting our activity.
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return bookModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView bookTitleTV, bookYearTV, bookAuthorTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            bookTitleTV = itemView.findViewById(R.id.idTVBookTitle);
            bookYearTV = itemView.findViewById(R.id.idTVBookYear);
            bookAuthorTV = itemView.findViewById(R.id.idTVBookAuthor);
        }
    }
}
