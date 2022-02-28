package com.example.sqliteapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class ListRecyclerAdapter extends RecyclerView.Adapter<ListRecyclerAdapter.ViewHolder> {

    private ArrayList<Contact> contacts = new ArrayList<>();

    private Context context;

    public ListRecyclerAdapter(Context context){
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = contacts.get(position);

        String activatedText;
        if (contact.isAdmin()){
            activatedText = context.getString(R.string.admin);
        }
        else{
            activatedText = context.getString(R.string.not_Admin);
        }


        holder.txtDetails.setText(String.format(context.getString(R.string.list_item),
                        contact.getFirstName(),
                        contact.getLastName(),
                        contact.getAge(),
                        contact.getId(),
                        activatedText));


    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtDetails;
        private ImageView imgDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            initViews();

        }

        private void initViews() {
            txtDetails = itemView.findViewById(R.id.txtDetails);
            imgDelete = itemView.findViewById(R.id.imgDelete);


            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteItem(getAdapterPosition());
                }
            });
        }


    }

    public void deleteItem(int position){
        this.contacts.remove(position);
        notifyDataSetChanged();
    }

    public void setContacts(ArrayList<Contact> contacts){
        this.contacts = contacts;
        notifyDataSetChanged();
    }
}
