package com.example.admin.pmpapplication;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class ExamDetailFragment extends Fragment implements View.OnClickListener {

    public String selected = null;
    public String exam_qn = null;
    public String exam_ans = null;
    String optionA = null;
    String optionB = null;
    String optionC = null;
    String optionD = null;
    String question = null;
    int n = 0;




    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_exam_detail, container, false);
//
//    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_exam_detail, container, false);

        RadioButton radA = (RadioButton) layout.findViewById(R.id.radio_A);
        radA.setOnClickListener(this);

        RadioButton radB = (RadioButton) layout.findViewById(R.id.radio_B);
        radB.setOnClickListener(this);

        RadioButton radC = (RadioButton) layout.findViewById(R.id.radio_C);
        radC.setOnClickListener(this);

        RadioButton radD = (RadioButton) layout.findViewById(R.id.radio_D);
        radD.setOnClickListener(this);

        Button submitButton = (Button) layout.findViewById(R.id.submit_button_id);
        submitButton.setOnClickListener(this); return layout;
    }

    @Override
    public void onStart(){

        super.onStart();



        try {

            SQLiteOpenHelper pmpDatabaseHelper = new PmpDatabaseHelper(getContext());
            SQLiteDatabase db = pmpDatabaseHelper.getReadableDatabase();

            Random rand = new Random();
            n = rand.nextInt(3)+1;

            Cursor cursor = db.query("QBANK", new String[]{"QUESTION_NO","QUESTION", "OPTIONA", "OPTIONB", "OPTIONC", "OPTIOND","ANSWER"},
                    "_ID = ?", new String[]{Integer.toString(n)}, null, null, null);

//            Cursor cursor = db.query("QBANK", new String[]{"QUESTION_NO","QUESTION", "OPTIONA", "OPTIONB", "OPTIONC", "OPTIOND","ANSWER"},
//                    "_ID = ?", new String[]{Integer.toString(1)}, null, null, null);

            if (cursor.moveToFirst()){
                Log.d("tag2","moving to first");
                int num =  cursor.getInt(0);
                question = cursor.getString(1);
                optionA = cursor.getString(2);
                optionB = cursor.getString(3);
                optionC = cursor.getString(4);
                optionD = cursor.getString(5);
                exam_ans = cursor.getString(6);

                View view = getView();
                TextView question_details = (TextView) view.findViewById(R.id.question_details);
                TextView radioA = (TextView) view.findViewById(R.id.radio_A);
                TextView radioB = (TextView) view.findViewById(R.id.radio_B);
                TextView radioC = (TextView) view.findViewById(R.id.radio_C);
                TextView radioD = (TextView) view.findViewById(R.id.radio_D);

                question_details.setText(question);
                radioA.setText(optionA);
                radioB.setText(optionB);
                radioC.setText(optionC);
                radioD.setText(optionD);
            }
            else {
                Log.d("tag3","nothing found");
            }

        }
        catch (Exception e){
            Toast toast = Toast.makeText(getContext(),"DB not available", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void onClick(View v){
//        getView()
//        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.options);
//        int id = radioGroup.getCheckedRadioButtonId();
        switch (v.getId()) {
            case R.id.radio_A:  selected = optionA; Log.d("tag10",n + " is clicked"); break;
            case R.id.radio_B:  selected = optionB; break;
            case R.id.radio_C:  selected = optionC; break;
            case R.id.radio_D:  selected = optionD; break;
            case R.id.submit_button_id: onSubmitClicked(); break;

        }
    }

    public void onBackPressed(){

    }

    public void onSubmitClicked(){
        if (selected == null){
            Toast toast = Toast.makeText(getContext(), "Please select an option", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            Toast toast = Toast.makeText(getContext(), "selected option is " + selected, Toast.LENGTH_SHORT);
            toast.show();
            insertAnswers(question, exam_ans,selected);

            if (ExamActivity.loop < 4) {
                Fragment frag = new ExamDetailFragment();
                FragmentTransaction trans = getFragmentManager().beginTransaction();
                trans.replace(R.id.detail_frag, frag);
                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                trans.commit();
                Log.d("tag12","loop value is " + ExamActivity.loop);
                ExamActivity.loop++;
            }
            else {
                Fragment frag1 = new ResultSummaryFragment();
                FragmentTransaction trans = getFragmentManager().beginTransaction();
                trans.replace(R.id.detail_frag, frag1);
                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                trans.commit();

            }

//
        }
    }

    public void insertAnswers(String parm1, String parm2, String parm3){
        ContentValues resValues = new ContentValues();

        resValues.put("QN",parm1);
        resValues.put("OP_CORRECT",parm2);
        resValues.put("OP_SELECTED",parm3);
        if (parm2.equals(parm3)) {
            ExamActivity.ctr++;
        }
        Log.d("tag15","ctr value is " + ExamActivity.ctr);
        resValues.put("DATED",ExamActivity.date_value);
        SQLiteOpenHelper pmpDatabaseHelper = new PmpDatabaseHelper(getContext());
        SQLiteDatabase db = pmpDatabaseHelper.getReadableDatabase();
        db.insert("RESULTS",null,resValues);
        Log.d("tag11",ExamActivity.date_value);
    }
    }



