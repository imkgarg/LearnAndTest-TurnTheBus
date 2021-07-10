package com.thirsty.learntest.ui;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.thirsty.learntest.R;
import com.thirsty.learntest.adapters.CardRecyclerViewAdapter;
import com.thirsty.learntest.models.FlashCard;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.Duration;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import java.util.ArrayList;
import java.util.List;

import lombok.val;

public class LearnActivity extends AppCompatActivity implements CardStackListener, View.OnClickListener {

    private CardStackLayoutManager cardStackLayoutManager;
    private CardStackView cardStackView;
    private CardRecyclerViewAdapter cardStackAdapter;
    private Button nextCard;
    private List<FlashCard> flashCardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        cardStackView = findViewById(R.id.card_stack_view);
        nextCard = findViewById(R.id.buttonNextCard);

        cardStackLayoutManager = new CardStackLayoutManager(LearnActivity.this, this);
        nextCard.setOnClickListener(this);

        setupFlashCardList();
        cardStackAdapter = new CardRecyclerViewAdapter(LearnActivity.this, flashCardList);
        initialize();
    }

    private void setupFlashCardList() {
        flashCardList = new ArrayList<>();
        flashCardList.add(new FlashCard("What is the capital of India", "Kolkata",
                "Patna", "New Delhi", "Mumbai", "New Delhi"));
        flashCardList.add(new FlashCard("What is the capital of West Bengal", "Kolkata",
                "Kharagpur", "Durgapur", "Asansol", "Kolkata"));
        flashCardList.add(new FlashCard("What is the capital of Bihar", "Gaya",
                "Patna", "Nalanda", "Darbanga", "Patna"));
        flashCardList.add(new FlashCard("What is the capital of Jharkhand", "Bokaro Steel City",
                "Ranchi", "Jamshedpur", "Dhanbad", "Ranchi"));

    }

    @Override
    public void onCardDragging(Direction direction, float ratio) {

    }

    @Override
    public void onCardSwiped(Direction direction) {

    }

    @Override
    public void onCardRewound() {

    }

    @Override
    public void onCardCanceled() {

    }

    @Override
    public void onCardAppeared(View view, int position) {

    }

    @Override
    public void onCardDisappeared(View view, int position) {

    }

    private void initialize() {
        cardStackLayoutManager.setStackFrom(StackFrom.None);
        cardStackLayoutManager.setVisibleCount(3);
        cardStackLayoutManager.setTranslationInterval(8.0f);
        cardStackLayoutManager.setScaleInterval(0.95f);
        cardStackLayoutManager.setSwipeThreshold(0.3f);
        cardStackLayoutManager.setMaxDegree(20.0f);
        cardStackLayoutManager.setDirections(Direction.HORIZONTAL);
        cardStackLayoutManager.setCanScrollHorizontal(true);
        cardStackLayoutManager.setCanScrollVertical(true);
        cardStackLayoutManager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual);
        cardStackLayoutManager.setOverlayInterpolator(new LinearInterpolator());
        cardStackView.setLayoutManager(cardStackLayoutManager);
        cardStackView.setAdapter(cardStackAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonNextCard) {
            SwipeAnimationSetting swipeAnimationSetting = new SwipeAnimationSetting.Builder().
                    setDirection(Direction.Right)
                    .setInterpolator(new AccelerateInterpolator()).build();
            cardStackLayoutManager.setSwipeAnimationSetting(swipeAnimationSetting);
            cardStackView.swipe();
        }
    }
}
