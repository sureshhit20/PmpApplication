package com.example.admin.pmpapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExamActivity extends AppCompatActivity {

        public static String date_value;
    public static int loop = 0;
    public static int ctr = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy_hh:mm:ss");
        date_value = sdf.format(date);


        Fragment frag = new ResultsDetailedFragment();
 //      Fragment frag = new ExamDetailFragment();
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.detail_frag,frag);
        trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        trans.commit();
//        ExamDetailFragment frag = (ExamDetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_frag);
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.detail_frag, frag).commit();
    }
}
