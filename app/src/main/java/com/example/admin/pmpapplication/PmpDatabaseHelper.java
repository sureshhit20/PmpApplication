package com.example.admin.pmpapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PmpDatabaseHelper extends SQLiteOpenHelper {

    static String DB_NAME = "pmp";
    static int DB_VERSION = 1;

    PmpDatabaseHelper(Context context){
        super(context, DB_NAME, null,DB_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
            updateMyDatabase(db, 0, DB_VERSION);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE QBANK (_id INTEGER PRIMARY KEY AUTOINCREMENT, QUESTION_NO INT, QUESTION TEXT, OPTIONA TEXT, OPTIONB TEXT, " +
                    "OPTIONC TEXT, OPTIOND TEXT, ANSWER TEXT)");
            insertQuestion(db, 1,"What is 2+2?", "Answer is 1", "Answer is 2", "Answer is 3", "Answer is 4", "D");
            insertQuestion(db, 2,"What is 2+3?", "Answer is 1", "Answer is 2", "Answer is 5", "Answer is 4", "C");
            insertQuestion(db, 3,"What is 2+4?", "Answer is 1", "Answer is 6", "Answer is 5", "Answer is 4", "B");
            db.execSQL("CREATE TABLE RESULTS (_id INTEGER PRIMARY KEY AUTOINCREMENT, QN TEXT, OP_CORRECT TEXT, OP_SELECTED TEXT)");

        }
        }

        public void insertQuestion(SQLiteDatabase db, int question_no, String question, String optionA, String optionB, String optionC, String optionD, String answer){
            ContentValues qbankValues = new ContentValues();
            qbankValues.put("QUESTION_NO",question_no);
            qbankValues.put("QUESTION", question);
            qbankValues.put("OPTIONA", optionA);
            qbankValues.put("OPTIONB", optionB);
            qbankValues.put("OPTIONC", optionC);
            qbankValues.put("OPTIOND", optionD);
            long b = db.insert("QBANK",null,qbankValues);

        }

}