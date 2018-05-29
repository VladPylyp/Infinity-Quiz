package com.stormarts.infinityquiz.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stormarts.infinityquiz.R;

import java.util.List;

public class AnswersRecyclerViewAdapter extends RecyclerView.Adapter<AnswersViewHolder> {

    private List<String> answersList;
    private OnAnswerClickListener mOnItemClickListener;

    public AnswersRecyclerViewAdapter(List<String> answersList, OnAnswerClickListener listener) {
        this.answersList = answersList;
        this.mOnItemClickListener = listener;
    }

    @NonNull
    @Override
    public AnswersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.text_answer_list_content, parent, false);
        return new TextAnswerViewHolder(view, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AnswersViewHolder holder, int position) {
        final String answer = answersList.get(holder.getAdapterPosition());
        holder.bindHolder(answer);
    }

    @Override
    public int getItemCount() {
        return answersList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }
}
