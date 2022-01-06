package com.example.xmasdraft;

import static com.example.xmasdraft.QuestionSetActivity.QUESTION_SET_ID;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import java.util.ArrayList;

public class MainMenuRecyclerAdapter extends RecyclerView.Adapter<MainMenuRecyclerAdapter.ViewHolder> {

    // We need an array list to pass the list of different contacts to the adapter class.
    // If we do not initialise this array list, we will get a null pointer exception.
    private ArrayList<QuestionSet> questionSets = new ArrayList<>();

    private Context context;

    public MainMenuRecyclerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // The return type of this method is a ViewHolder, indicating that this is the place to instantiate our View Holder.

        // We are creating a View object:

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_menu_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // We can directly access txtContactName from the ViewHolder class despite it being a
        // private attribute, because it is a subclass of the contactsRecyclerAdapter class.
        holder.txtQuestionSetName.setText(questionSets.get(position).getName());

        holder.txtQuestionSetDescription.setText(questionSets.get(position).getDescription());

        holder.imgQuestionSet.setImageResource(questionSets.get(position).getImageId());

        // We are expanding / collapsing the Card View:

        // If it is expanded, we need to expand the Card View:
        if (questionSets.get(holder.getAdapterPosition()).isExpanded()) {
            // We want an animation while changing the visibility:
            TransitionManager.beginDelayedTransition(holder.relMainMenuItem);

            // We are using the .setVisibility() function to change the visibility
            holder.cvExpandedMainMenuItem.setVisibility(View.VISIBLE);


        } else {
            TransitionManager.beginDelayedTransition(holder.relMainMenuItem);
            holder.cvExpandedMainMenuItem.setVisibility(View.GONE);

        }
    }


    @Override
    public int getItemCount() {
        // This method returns the number of items.
        // Since our contacts are stored in the contacts array list, we can just return the size of that.
        return questionSets.size();
    }


    // We are using an inner-class:
    // The view holder class will hold the view for every item in the Recycler View.
    // It is responsible for generating the View objects.
    public class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout relMainMenuItem;
        private TextView txtQuestionSetName, txtQuestionSetDescription;
        private ImageView imgQuestionSet;
        private CardView cvCollapsedMainMenuItem, cvExpandedMainMenuItem;
        private Button btnStartQuestionSet;


        public ViewHolder(@NonNull View itemView) {
            // We can add all the elements in our layout one by one:
            super(itemView);


            // Initialising View Objects
            initViews();


            // We cannot implement View.OnClickListener as we would need to be in this method.
            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // We are using this to get the current question set:
                    QuestionSet questionSet = questionSets.get(getAdapterPosition());

                    switch (v.getId()) {

                        case R.id.cvCollapsedMainMenuItem:
                            // If the Relative View has been clicked, expand if collapsed; collapse if expanded:
                            questionSet.setExpanded(!questionSets.get(getAdapterPosition()).isExpanded());

                            // We normally would have used notifyDataSetChanged(), but because we are only
                            // changing a single item, we can use this instead:
                            notifyItemChanged(getAdapterPosition());
                            break;

                        case R.id.btnStartQuestionSet:
                            // We are launching the Question Set from which we will retrieve which
                            // question set was clicked and change the questions accordingly.

                            context.startActivity(new Intent(context, QuestionSetActivity.class)
                                    // Importing QUESTION_SET_ID from Utils
                                    .putExtra(QUESTION_SET_ID, questionSets.get(getAdapterPosition()).getQuestionSetID()));


                    }
                }
            };

            cvCollapsedMainMenuItem.setOnClickListener(onClickListener);
            btnStartQuestionSet.setOnClickListener(onClickListener);


        }

        private void initViews() {

            // Inside an activity, we cannot simply use the fb function and need the following syntax:
            relMainMenuItem = itemView.findViewById(R.id.relMainMenuItem);
            txtQuestionSetName = itemView.findViewById(R.id.txtQuestionSetName);
            imgQuestionSet = itemView.findViewById(R.id.imgQuestionSet);
            txtQuestionSetDescription = itemView.findViewById(R.id.txtQuestionSetDescription);

            cvCollapsedMainMenuItem = itemView.findViewById(R.id.cvCollapsedMainMenuItem);
            cvExpandedMainMenuItem = itemView.findViewById(R.id.cvExpandedMainMenuItem);

            btnStartQuestionSet = itemView.findViewById(R.id.btnStartQuestionSet);
        }


    }


    public void setQuestionSets(ArrayList<QuestionSet> questionSets) {
        // We will later use this method to pass the data into the contacts array list.
        this.questionSets = questionSets;

        // When we change the contacts ArrayList, we need to refresh the data inside the Recycler View:
        notifyDataSetChanged();
    }
}
