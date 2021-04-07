/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.tblquestion;

import java.io.Serializable;

/**
 *
 * @author ANH NGUYEN
 */
public class QuestionDTO implements Serializable {

    private String idQuestion, questionContent, answerCorrect, date, subjectId, status;

    public QuestionDTO() {
    }

    public QuestionDTO(String questionContent, String answerCorrect) {
        this.questionContent = questionContent;
        this.answerCorrect = answerCorrect;
    }

    public QuestionDTO(String idQuestion, String questionContent, String answerCorrect, String subjectId, String status) {
        this.idQuestion = idQuestion;
        this.questionContent = questionContent;
        this.answerCorrect = answerCorrect;
        this.subjectId = subjectId;
        this.status = status;
    }

    public QuestionDTO(String idQuestion, String questionContent, String answerCorrect, String status) {
        this.idQuestion = idQuestion;
        this.questionContent = questionContent;
        this.answerCorrect = answerCorrect;
        this.status = status;
    }

    public QuestionDTO(String idQuestion, String questionContent, String answerCorrect, String date, String subjectId, String status) {
        this.idQuestion = idQuestion;
        this.questionContent = questionContent;
        this.answerCorrect = answerCorrect;
        this.date = date;
        this.subjectId = subjectId;
        this.status = status;
    }

    public String getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(String idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getAnswerCorrect() {
        return answerCorrect;
    }

    public void setAnswerCorrect(String answerCorrect) {
        this.answerCorrect = answerCorrect;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
