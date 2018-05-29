package com.stormarts.infinityquiz.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.stormarts.infinityquiz.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TextAnswerViewHolder extends AnswersViewHolder {


    @BindView(R.id.answer_text_view)
    TextView answerTextView;

    private View mView;

    public TextAnswerViewHolder(View view, OnAnswerClickListener listener) {
        super(view, listener);
        mView = view;
        ButterKnife.bind(this, mView);
    }


    @Override
    public void bindHolder(String answer) {
        answerTextView.setText(answer);
    }

    @Override
    public void onClick(View v) {
        mListener.onClick(getAdapterPosition(), answerTextView.getText().toString());
    }
}
