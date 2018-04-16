package com.example.admin.pmpapplication;

public class Pmpq {

    public String Question;
    private String Correct;
    private String Selected;
    private String dated;

    public String getSelected() {
        return Selected;
    }

    public void setSelected(String selected) {
        Selected = selected;
    }

    public String getDated() {
        return dated;
    }

    public void setDated(String dated) {
        this.dated = dated;
    }

    public String getCorrect() {
        return Correct;
    }

    public void setCorrect(String correct) {
        Correct = correct;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

}
