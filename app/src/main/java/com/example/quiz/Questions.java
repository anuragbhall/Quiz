package com.example.quiz;

public class Questions {

    private String questions;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int answerNumber;

    public Questions(String Question, String Option1, String Option2, String Option3, String Option4, int answerNumber) {
        this.questions = Question;
        this.option1 = Option1;
        this.option2 = Option2;
        this.option3 = Option3;
        this.option4 = Option4;
        this.answerNumber = answerNumber;
    }

    public Questions(){}

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public int getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(int answerNumber) {
        this.answerNumber = answerNumber;
    }
}
