package com.example.admin.pmpapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultSummaryFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result_summary, container, false);


    }

    public void onStart(){
        super.onStart();
        View frag_view = getView();
        TextView result_text = frag_view.findViewById(R.id.textTitle);
        result_text.setText("You have got " + ExamActivity.ctr + " answers correct \n out of 5 questions");

    }

}
