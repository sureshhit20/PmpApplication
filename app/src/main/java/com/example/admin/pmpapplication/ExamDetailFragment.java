package com.example.admin.pmpapplication;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class ExamDetailFragment extends Fragment {

    public String selected = null;
    public String exam_qn = null;
    public String exam_ans = null;
    String optionA = null;
    String optionB = null;
    String optionC = null;
    String optionD = null;
    String question = null;

    SQLiteOpenHelper pmpDatabaseHelper = new PmpDatabaseHelper(getContext());
    SQLiteDatabase db = pmpDatabaseHelper.getReadableDatabase();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exam_detail, container, false);
    }

    @Override
    public void onStart(){

        super.onStart();
        try {

            Cursor cursor = db.query("QBANK", new String[]{"QUESTION_NO","QUESTION", "OPTIONA", "OPTIONB", "OPTIONC", "OPTIOND","ANSWER"},
                    "_ID = ?", new String[]{Integer.toString(1)}, null, null, null);

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

    public void onRadioButtonClicked(View view){
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.options);
        int id = radioGroup.getCheckedRadioButtonId();
        switch (id) {
            case R.id.radio_A:  selected = optionA; break;
            case R.id.radio_B:  selected = "B"; break;
            case R.id.radio_C:  selected = "C"; break;
            case R.id.radio_D:  selected = "D"; break;

        }
    }

    public void onSubmitClicked(View view){
        if (selected == null){
            Toast toast = Toast.makeText(getContext(), "Please select an option", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            Toast toast = Toast.makeText(getContext(), "selected option is " + selected, Toast.LENGTH_SHORT);
            toast.show();
//            insertAnswers(question, exam_ans,selected);
        }
    }

//    public void insertAnswers(String parm1, String parm2, String parm3){
//        ContentValues resValues = new ContentValues();
//
//        resValues.put("QN",parm1);
//        resValues.put("OP_CORRECT",parm2);
//        resValues.put("OP_SELECTED",parm3);
//        db.insert("RESULTS",null,resValues);
//    }
    }



