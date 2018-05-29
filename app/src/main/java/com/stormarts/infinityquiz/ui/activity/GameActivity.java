package com.stormarts.infinityquiz.ui.activity;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.stormarts.infinityquiz.R;
import com.stormarts.infinityquiz.model.Level;
import com.stormarts.infinityquiz.presentation.view.GameView;
import com.stormarts.infinityquiz.presentation.presenter.GamePresenter;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.stormarts.infinityquiz.ui.adapter.AnswersRecyclerViewAdapter;
import com.stormarts.infinityquiz.ui.adapter.OnAnswerClickListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameActivity extends BaseActivity implements GameView {
    public static final String TAG = "GameActivity";

    @InjectPresenter
    GamePresenter mGamePresenter;

    @BindView(R.id.game_activity_layout)
    View mContentView;

    @BindView(R.id.question_text_view)
    TextView questionTextView;

    @BindView(R.id.answersRecycleView)
    RecyclerView answersRecyclerView;

    @BindView(R.id.score_text_view)
    TextView scoreTextView;

    @BindView(R.id.coins_text_view)
    TextView coinsTextView;

    @BindView(R.id.questions_count_text_view)
    TextView questionsCountTextView;

    private OnAnswerClickListener onAnswerClickListener;
    private AnswersRecyclerViewAdapter answersRecyclerViewAdapter;
    private List<String> answersList;
    private RecyclerView.LayoutManager ansqwersRVLayoutManager;


    private int selectedLevel = Level.EASY;
    private int questionsCount;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, GameActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);


        mBaseContentView = mContentView;

        answersList = new ArrayList<>();
        onAnswerClickListener = ((position, answer) -> {
            mGamePresenter.selectAnswer(answer);
        }
        );

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            ansqwersRVLayoutManager = new GridLayoutManager(this, 2);
        } else {
            ansqwersRVLayoutManager = new LinearLayoutManager(this);
        }

        answersRecyclerView.setLayoutManager(ansqwersRVLayoutManager);
        answersRecyclerViewAdapter = new AnswersRecyclerViewAdapter(answersList, onAnswerClickListener);
        answersRecyclerView.setAdapter(answersRecyclerViewAdapter);

        if(savedInstanceState == null){
            selectedLevel = getIntent().getIntExtra(Level.SELECTED, Level.EASY);

                switch(selectedLevel){
                    case Level.EASY:{
                        mGamePresenter.loadLevel(Level.EASY_STRING);
                        break;
                    }
                    case Level.MID:{
                        mGamePresenter.loadLevel(Level.MID_STRING);
                        break;
                    }
                    case Level.HARD:{
                        mGamePresenter.loadLevel(Level.HARD_STRING);
                        break;
                    }
                    default:{

                    }

                }
        }


    }



    @OnClick(R.id.game_activity_layout)
    void onContentClick(View v){
        toggle();
    }

    @Override
    public void setCurrentQuestion(String currentQuestion) {
        questionTextView.setText(currentQuestion);
    }

    @Override
    public void setCurrentAnswers(List<String> answersList) {
        if(answersList != null){
            this.answersList.clear();
            this.answersList.addAll(answersList);
            Collections.shuffle(this.answersList);
            this.answersRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setScore(int score) {
        scoreTextView.setText(String.valueOf(score));
    }

    @Override
    public void setCoins(int coins) {
        coinsTextView.setText(String.valueOf(coins));
    }

    @Override
    public void showNewLevelAskDialog() {
        Log.d(TAG, "showNewLevelAskDialog: ");
        mGamePresenter.loadNextLevel();
    }


    @Override
    public void setCurrentQuestionNumber(int number) {
        questionsCountTextView.setText(String.format(Locale.ENGLISH,"%d/%d", number, questionsCount));
    }

    @Override
    public void setQuestionsCount(int questionsCount) {
        this.questionsCount = questionsCount;
    }
}
