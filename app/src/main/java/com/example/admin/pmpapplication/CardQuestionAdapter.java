package com.example.admin.pmpapplication;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CardQuestionAdapter extends RecyclerView.Adapter<CardQuestionAdapter.ViewHolder>{

    private ArrayList<Pmpq> pmpqArrayList;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        TextView textView1 ;
        TextView textView2 ;
        TextView textView3 ;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
             textView1 = (TextView)cardView.findViewById(R.id.info_question);
            textView2 = (TextView)cardView.findViewById(R.id.info_correct);
            textView3 = (TextView)cardView.findViewById(R.id.info_selected);

        }

    }

    public CardQuestionAdapter(ArrayList<Pmpq> gh){
        pmpqArrayList = gh;
        Log.d("suresh","mot");
        for(int i=0;i<pmpqArrayList.size();i++){
            Pmpq as=pmpqArrayList.get(i);
            Log.d("ok intiaizi",as.Question);
        }
    }

    public int getItemCount(){
        return pmpqArrayList.size();
    }

    public CardQuestionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
         CardView cv = (CardView) LayoutInflater.from(parent.getContext()) .inflate(R.layout.card_questions, parent, false);
        return new ViewHolder(cv);
    }


    public void onBindViewHolder(ViewHolder holder, int position) {
        Pmpq instance=pmpqArrayList.get(position);
        holder.textView1.setText(instance.getQuestion());
        holder.textView2.setText(instance.getCorrect());
        holder.textView3.setText(instance.getSelected());
    }

}
