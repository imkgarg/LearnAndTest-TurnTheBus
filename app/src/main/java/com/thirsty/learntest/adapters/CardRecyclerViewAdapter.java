package com.thirsty.learntest.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.thirsty.learntest.R;
import com.thirsty.learntest.models.Course;
import com.thirsty.learntest.models.FlashCard;
import com.thirsty.learntest.ui.CourseDetailsActivity;
import com.thirsty.learntest.ui.LearnActivity;
import com.thirsty.learntest.utils.Constants;
import com.wajahatkarim3.easyflipview.EasyFlipView;
import com.yuyakaido.android.cardstackview.CardStackView;

import java.util.List;

public class CardRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String TAG = "CardRecyclerViewAdapter";

    private List<FlashCard> flashCardList;
    private LearnActivity learnActivity;
    private Boolean isEmptyList;

    public CardRecyclerViewAdapter(LearnActivity learnActivity,
                                   List<FlashCard> flashCardList) {
        this.flashCardList = flashCardList;
        this.learnActivity = learnActivity;
        this.isEmptyList = this.flashCardList.size() == 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (this.isEmptyList) {
            return new EmptyViewHolder(LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.empty_layout, parent, false));
        }

        return new CardViewHolder(learnActivity, LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_learn, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if (!this.isEmptyList) {
            FlashCard flashCard = flashCardList.get(position);

            CardViewHolder cardViewHolder = (CardViewHolder) holder;
            cardViewHolder.question.setText(flashCard.getQuestion());
            cardViewHolder.option1.setText(flashCard.getOption1());
            cardViewHolder.option2.setText(flashCard.getOption2());
            cardViewHolder.option3.setText(flashCard.getOption3());
            cardViewHolder.option4.setText(flashCard.getOption4());

        } else {
            EmptyViewHolder emptyViewHolder = (EmptyViewHolder) holder;
            emptyViewHolder.emptyTextView.setText("No Course Found!!! Pease retry");
        }
    }

    @Override
    public int getItemCount() {
        return this.isEmptyList ? 1 : flashCardList.size();
    }

    public class EmptyViewHolder extends RecyclerView.ViewHolder {

        TextView emptyTextView;

        public EmptyViewHolder(View view) {
            super(view);
            emptyTextView = view.findViewById(R.id.tvEmpty);
        }

    }

    public class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Context context;
        EasyFlipView flipView;
        TextView question, option1, option2, option3, option4, answer, correctAnswer;

        public CardViewHolder(final Context context, final View view) {
            super(view);
            this.context = context;

            flipView = view.findViewById(R.id.easyflipLearn);
            question = view.findViewById(R.id.tvFlashCardQuestion);
            option1 = view.findViewById(R.id.tvFlashCardOption1);
            option2 = view.findViewById(R.id.tvFlashCardOption2);
            option3 = view.findViewById(R.id.tvFlashCardOption3);
            option4 = view.findViewById(R.id.tvFlashCardOption4);

            answer = view.findViewById(R.id.tvFlashCardAnswer);
            correctAnswer = view.findViewById(R.id.tvFlashCardCorrectAnswer);

            option1.setOnClickListener(this);
            option2.setOnClickListener(this);
            option3.setOnClickListener(this);
            option4.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            FlashCard flashCard = flashCardList.get(getAdapterPosition());
            TextView selected = v.findViewById(v.getId());
            if (flashCard.getAnswer().equalsIgnoreCase(selected.getText().toString())) {
                correctAnswer.setVisibility(View.GONE);
                answer.setText("Bravo!!! Well Done");
            } else {

                String answerText = "Correct answer is " + flashCard.getAnswer();
                correctAnswer.setText(answerText);
                correctAnswer.setVisibility(View.VISIBLE);
                answer.setText("Oops!! This is incorrect.");
            }

            flipView.flipTheView();

            /*switch (v.getId()) {
                case R.id.tvFlashCardOption1:
                    break;
                case R.id.tvFlashCardOption2:
                    break;
                case R.id.tvFlashCardOption3:
                    break;
                case R.id.tvFlashCardOption4:
                    break;
            }*/

        }
    }

}


