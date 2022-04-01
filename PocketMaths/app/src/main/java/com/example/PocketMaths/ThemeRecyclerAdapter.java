package com.example.PocketMaths;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;
import java.util.Objects;

public class ThemeRecyclerAdapter extends RecyclerView.Adapter<ThemeRecyclerAdapter.ViewHolder> {

    private Context context;

    private DatabaseHelper databaseHelper;
    private int[] themeIds;


    public ThemeRecyclerAdapter(Context context) {
        this.context = context;
        this.databaseHelper = new DatabaseHelper(context);
        this.themeIds = new int[]{R.drawable.theme1, R.drawable.theme2, R.drawable.theme3, R.drawable.theme4};
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // The return type of this method is a ViewHolder, indicating that this is the place to instantiate our View Holder.

        // We are creating a View object:
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.theme_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // Setting the appropriate image resource from the array:
        holder.imgTheme.setImageResource(themeIds[position]);

    }


    @Override
    public int getItemCount() {
        // This method returns the number of items.
        return themeIds.length;

    }


    // We are using an inner-class:
    // The view holder class will hold the view for every item in the Recycler View.
    // It is responsible for generating the View objects.
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgTheme;


        public ViewHolder(@NonNull View itemView) {
            // We can add all the elements in our layout one by one:
            super(itemView);


            // Initialising View Objects
            initViews();

        }

        private void initViews() {
            // Inside an activity, we cannot simply use the fb function and need the following syntax:
           imgTheme = itemView.findViewById(R.id.imgTheme);

           imgTheme.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {

                   switch (getAdapterPosition()){

                       case (0):
                           setTheme(R.style.Theme_PocketMaths);
                           break;

                       case (1) :
                           setTheme(R.style.RedGreen);
                           break;

                       case (2):
                           setTheme(R.style.GreenYellow);
                            break;

                       case (3):
                           setTheme(R.style.BlueGreen);
                           break;
                   }


               }
           });


        }


    }
    public void setTheme(int id){
        Utils.getInstance().setThemeId(id);
        databaseHelper.setTheme(id);
        context.startActivity(new Intent(context, SettingsActivity.class));
    }




}

