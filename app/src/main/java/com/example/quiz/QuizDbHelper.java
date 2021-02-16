package com.example.quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.quiz.QuizContract.*;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QUIZ.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_DATA_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTIONS + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NUMBER + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_DATA_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable() {
        Questions q1 = new Questions("A is correct", "A", "B", "C", "D", 1);
        addQuestion(q1);
        Questions q2 = new Questions("A is correct", "A", "B", "C", "D", 3);
        addQuestion(q2);
        Questions q3 = new Questions("A is correct", "A", "B", "C", "D", 2);
        addQuestion(q3);
        Questions q4 = new Questions("A is correct", "A", "B", "C", "D", 4);
        addQuestion(q4);
    }

    private void addQuestion(Questions questions) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTIONS, questions.getQuestions());
        cv.put(QuestionsTable.COLUMN_OPTION1, questions.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, questions.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, questions.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, questions.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NUMBER, questions.getAnswerNumber());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Questions> getAllQuestions() {
        List<Questions> questionsList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);
        if (cursor.moveToFirst()){
            do {
                Questions questions = new Questions();
                questions.setQuestions(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_QUESTIONS)));
                questions.setOption1(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                questions.setOption2(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                questions.setOption3(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                questions.setOption4(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                questions.setAnswerNumber(cursor.getInt(cursor.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NUMBER)));
                questionsList.add(questions);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return questionsList;
    }
}
