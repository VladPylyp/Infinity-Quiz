package com.stormarts.infinityquiz.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class AnswersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    OnAnswerClickListener mListener;
    private View mView;

    public AnswersViewHolder(View view, OnAnswerClickListener listener) {
        super(view);
        this.mListener = listener;
        this.mView = view;
        mView.setOnClickListener(this);
    }

    public void bindHolder(String answer){
    }

    @Override
    public void onClick(View v) {
        mListener.onClick(getAdapterPosition(), "null");
    }
}
