package com.example.flashcarddemo;

import static com.example.flashcarddemo.DeckViewActivity.DECK_ID;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;


public class DeckRecyclerAdapter extends RecyclerView.Adapter<DeckRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<Deck> decks;


    public DeckRecyclerAdapter(Context context, ArrayList<Deck> decks){
        this.context = context;
        this.decks = decks;
    }

    @NonNull
    @Override
    public DeckRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.deck_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeckRecyclerAdapter.ViewHolder holder, int position) {
        holder.txtDeckName.setText(decks.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return decks.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cvDeck;
        TextView txtDeckName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews();
        }

        private void initViews() {
            cvDeck = itemView.findViewById(R.id.cvDeck);
            txtDeckName = itemView.findViewById(R.id.txtDeckName);

            cvDeck.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.cvDeck) {
                context.startActivity(new Intent(context, DeckViewActivity.class).putExtra(DECK_ID, decks.get(getAdapterPosition()).getId()));
            }
        }
    }
}
