package com.example.admin.pmpapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ExamActivity extends AppCompatActivity {

    public String temp = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        SQLiteOpenHelper pmpDatabaseHelper = new PmpDatabaseHelper(this);
        try {
            pmpDatabaseHelper.getDatabaseName();
            SQLiteDatabase db = pmpDatabaseHelper.getReadableDatabase();

            Cursor cursor = db.query("QBANK", new String[]{"QUESTION_NO","QUESTION", "OPTIONA", "OPTIONB", "OPTIONC", "OPTIOND"},
                    "_ID = ?", new String[]{Integer.toString(1)}, null, null, null);

            if (cursor.moveToFirst()){
                Log.d("tag2","moving to first");
                int num =  cursor.getInt(0);
                String question = cursor.getString(1);
                String optionA = cursor.getString(2);
                String optionB = cursor.getString(3);
                String optionC = cursor.getString(4);
                String optionD = cursor.getString(5);

                TextView question_details = (TextView) findViewById(R.id.question_details);
                TextView radioA = (TextView) findViewById(R.id.radio_A);
                TextView radioB = (TextView) findViewById(R.id.radio_B);
                TextView radioC = (TextView) findViewById(R.id.radio_C);
                TextView radioD = (TextView) findViewById(R.id.radio_D);

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
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void onRadioButtonClicked(View view){
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.options);
        int id = radioGroup.getCheckedRadioButtonId();
        switch (id) {
            case R.id.radio_A:  temp = "A"; break;
            case R.id.radio_B:  temp = "B"; break;
            case R.id.radio_C:  temp = "C"; break;
            case R.id.radio_D:  temp = "D"; break;

        }
    }

    public void onSubmitClicked(View view){
        if (temp == null){
            Toast toast = Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            Toast toast = Toast.makeText(this, "selected option is " + temp, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
