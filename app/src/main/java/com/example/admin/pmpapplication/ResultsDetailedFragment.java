package com.example.admin.pmpapplication;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultsDetailedFragment extends Fragment {

    ArrayList<Pmpq> pmpq_Al = new ArrayList<Pmpq>();
    Pmpq pmpq = new Pmpq();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_results_detailed, container, false);
//    }


//    public void onStart() {
//
//        super.onStart();

        try {

            SQLiteOpenHelper pmpDatabaseHelper = new PmpDatabaseHelper(getContext());
            SQLiteDatabase db = pmpDatabaseHelper.getReadableDatabase();


            Cursor cursor3 = db.query("RESULTS",
                    new String[]{"QN", "OP_CORRECT", "OP_SELECTED", "DATED"}, null, null,
                    null, null, null);

            cursor3.moveToFirst();

            while (cursor3.moveToNext()) {
                pmpq=new Pmpq();
                pmpq.setQuestion(cursor3.getString(0));
                pmpq.setCorrect(cursor3.getString(1));
                pmpq.setSelected(cursor3.getString(2));
                pmpq.setDated(cursor3.getString(3));
                pmpq_Al.add(pmpq);

            }

            Log.d("asda", String.valueOf(pmpq_Al.size()));


        } catch (Exception e) { }
        View v=inflater.inflate(R.layout.fragment_results_detailed, container, false);

        RecyclerView questionRecycler = (RecyclerView)v.findViewById(R.id.question_recycler);
        questionRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        CardQuestionAdapter adapter = new CardQuestionAdapter(pmpq_Al);
        questionRecycler.setAdapter(adapter);
        return questionRecycler;

//    }


    }
}
