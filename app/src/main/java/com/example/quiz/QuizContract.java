package com.example.quiz;

import android.provider.BaseColumns;

public class QuizContract {

    private QuizContract() {
    }

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_question";
        public static final String COLUMN_QUESTIONS = "QUESTION";
        public static final String COLUMN_OPTION1 = "OPTION1";
        public static final String COLUMN_OPTION2 = "OPTION2";
        public static final String COLUMN_OPTION3 = "OPTION3";
        public static final String COLUMN_OPTION4 = "OPTION4";
        public static final String COLUMN_ANSWER_NUMBER = "ANSWER_NUMBER";

    }
}
