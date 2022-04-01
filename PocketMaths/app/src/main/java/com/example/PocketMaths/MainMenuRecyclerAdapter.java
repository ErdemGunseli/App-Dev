package com.example.PocketMaths;

import static com.example.PocketMaths.QuestionSetActivity.QUESTION_SET_ID;

import android.annotation.SuppressLint;
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

/**
 * This class is required to efficiently display View objects containing data from instances of QuestionSet in the form of a
 * scrollable list.
 * It is used to display the question sets in the menu, along with some additional details, images.
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * MainMenuRecyclerAdapter extends RecyclerView.Adapter<MainMenuRecyclerAdapter.ViewHolder>
 * to have access to RecyclerView methods.
 * MainMenuRecyclerAdapter has inner class ViewHolder to store content to be displayed on each
 * RecyclerView container.
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
public class MainMenuRecyclerAdapter extends RecyclerView.Adapter<MainMenuRecyclerAdapter.ViewHolder> {

    // We need an array list to pass the list of different contacts to the adapter class.
    // If we do not initialise this array list, we will get a null pointer exception.
    private ArrayList<QuestionSet> questionSets = new ArrayList<>();

    private Context context;

    /**
     * Constructor
     *
     * @param context Required to get strings from resources.
     */
    public MainMenuRecyclerAdapter(Context context) {
        this.context = context;
    }

    /**
     * Runs when an instance of ViewHolder is created.
     * Attaches the XML layout file 'account_history_item' to the ViewHolder.
     *
     * @param parent   Required to inflate XML layout file into a View object.
     * @param viewType Required for background processing.
     * @return ViewHolder object.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_menu_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Runs when an instance of ViewHolder object attaches to a container.
     *
     * @param holder   The instance of ViewHolder object, required for accessing data.
     * @param position The index of the ArrayList, required for accessing ArrayList items.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuestionSet questionSet = questionSets.get(position);
        holder.txtQuestionSetName.setText(questionSet.getName());
        // X Questions
        holder.txtNumberOfQuestions.setText(String.format(context.getString(R.string.number_of_questions), questionSet.getQuestions().length));

        // If the question set has been started, showing where the user left off and changing the
        // text of the 'Continue' Button to 'Start'.
        if (questionSet.getQuestions()[0].getAttempts() != 0) {
            // On Question X
            holder.txtCurrentQuestionIndex.setText(String.format(context.getString(R.string.on_question), (questionSet.getCurrentQuestionIndex() + 1)));
            // Continue
            holder.btnStartQuestionSet.setText(context.getString(R.string.continue_));
        } else {
            // Otherwise, reversing this process.
            // On Question X
            holder.txtCurrentQuestionIndex.setText("");
            // Continue
            holder.btnStartQuestionSet.setText(context.getString(R.string.start));
        }

        holder.txtQuestionSetDescription.setText(questionSet.getDescription());
        holder.imgQuestionSet.setImageResource(questionSet.getImageId());

        setCardViewState(holder);
    }

    /**
     * Expands or Collapses the CardView object by hiding or displaying an additional CardView object
     * with an animation.
     *
     * @param holder The ViewHolder object the state of which needs to be updated.
     */
    private void setCardViewState(ViewHolder holder) {
        // If the QuestionSet is expanded, show the expansion CardView:
        if (questionSets.get(holder.getAdapterPosition()).isExpanded()) {
            TransitionManager.beginDelayedTransition(holder.relMainMenuItem);
            holder.cvExpandedMainMenuItem.setVisibility(View.VISIBLE);
        } else {
            // Otherwise, hide the expansion CardView:
            TransitionManager.beginDelayedTransition(holder.relMainMenuItem);
            holder.cvExpandedMainMenuItem.setVisibility(View.GONE);

        }
    }

    @Override
    public int getItemCount() {
        // Avoid null pointer exception
        if (questionSets != null) {
            return questionSets.size();
        }
        return 0;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setQuestionSets(ArrayList<QuestionSet> questionSets) {
        this.questionSets = questionSets;
        notifyDataSetChanged();
    }

    /**
     * Collapses all QuestionSet objects.
     */
    public void collapseAll() {
        for (QuestionSet qs : questionSets) {
            qs.setExpanded(false);
        }
    }

    /**
     * This class is required to hold View objects for every item in the RecyclerView.
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * ViewHolder extends RecyclerView.ViewHolder to have access to its constructor.
     * ViewHolder implements View.OnCLickListener interface to detect touch input.
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private RelativeLayout relMainMenuItem;
        private TextView txtQuestionSetName, txtQuestionSetDescription, txtNumberOfQuestions, txtCurrentQuestionIndex;
        private ImageView imgQuestionSet;
        private CardView cvCollapsedMainMenuItem, cvExpandedMainMenuItem;
        private Button btnStartQuestionSet;

        /**
         * Constructor
         * @param itemView Required for super constructor
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            initViews();
        }

        /**
         * Initialises View objects.
         * Sets the activity's click listener to the appropriate View objects.
         */
        private void initViews() {
            // Outside an activity, we need the following syntax:
            relMainMenuItem = itemView.findViewById(R.id.relMainMenuItem);
            txtQuestionSetName = itemView.findViewById(R.id.txtQuestionSetName);
            imgQuestionSet = itemView.findViewById(R.id.imgQuestionSet);
            txtNumberOfQuestions = itemView.findViewById(R.id.txtNumberOfQuestions);
            txtCurrentQuestionIndex = itemView.findViewById(R.id.txtCurrentQuestionIndex);
            txtQuestionSetDescription = itemView.findViewById(R.id.txtQuestionSetDescription);
            cvCollapsedMainMenuItem = itemView.findViewById(R.id.cvCollapsedMainMenuItem);
            cvExpandedMainMenuItem = itemView.findViewById(R.id.cvExpandedMainMenuItem);
            btnStartQuestionSet = itemView.findViewById(R.id.btnStartQuestionSet);

            cvCollapsedMainMenuItem.setOnClickListener(this);
            btnStartQuestionSet.setOnClickListener(this);
        }


        /**
         * Determines which View object has been clicked and performs the appropriate action.
         * @param view Used to determine the View object clicked.
         */
        @Override
        public void onClick(View view) {
            QuestionSet currentQuestionSet = questionSets.get(getAdapterPosition());

            switch (view.getId()) {

                case (R.id.cvCollapsedMainMenuItem):

                    // Recording the expansion state of the QuestionSet object, so that it
                    // can be reversed:
                    boolean state = questionSets.get(getAdapterPosition()).isExpanded();

                    collapseAll();

                    currentQuestionSet.setExpanded(!state);

                    // Using notifyDataSetChanged() here causes the animations to not occur,
                    // so changing items one by one:
                    for (int i = 0; i < getItemCount(); i++) {
                        notifyItemChanged(i);
                    }
                    break;

                case (R.id.btnStartQuestionSet):
                    context.startActivity(new Intent(context, QuestionSetActivity.class)
                            .putExtra(QUESTION_SET_ID, currentQuestionSet.getId()));
            }
    }

}
}