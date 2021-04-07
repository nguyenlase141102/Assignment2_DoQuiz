/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.tblanswercontent;

import java.io.Serializable;

/**
 *
 * @author ANH NGUYEN
 */
public class AnswercontentDTO implements Serializable {

    String contentQuestion, idQuestion;
    String idAnswerContent;
    String studentChoose;
    boolean status;
    private String question;
    public AnswercontentDTO() {
    }

    public AnswercontentDTO(String contentQuestion, String idQuestion, boolean status) {
        this.contentQuestion = contentQuestion;
        this.idQuestion = idQuestion;
        this.status = status;
    }

    public AnswercontentDTO(String contentQuestion, String idQuestion, String idAnswerContent, boolean status) {
        this.contentQuestion = contentQuestion;
        this.idQuestion = idQuestion;
        this.idAnswerContent = idAnswerContent;
        this.status = status;
    }

    public AnswercontentDTO(String contentQuestion, String idQuestion, String idAnswerContent, boolean status, String question) {
        this.contentQuestion = contentQuestion;
        this.idQuestion = idQuestion;
        this.idAnswerContent = idAnswerContent;
        this.status = status;
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    
    
    
    
    public AnswercontentDTO(String contentQuestion, String idQuestion) {
        this.contentQuestion = contentQuestion;
        this.idQuestion = idQuestion;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getStudentChoose() {
        return studentChoose;
    }

    public void setStudentChoose(String studentChoose) {
        this.studentChoose = studentChoose;
    }

    public AnswercontentDTO(String idAnswerContent, String contentQuestiion, String idQuestion) {
        this.idAnswerContent = idAnswerContent;
        this.contentQuestion = contentQuestiion;
        this.idQuestion = idQuestion;
    }

    public String getContentQuestion() {
        return contentQuestion;
    }

    public void setContentQuestion(String contentQuestion) {
        this.contentQuestion = contentQuestion;
    }

    public String getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(String idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getIdAnswerContent() {
        return idAnswerContent;
    }

    public void setIdAnswerContent(String idAnswerContent) {
        this.idAnswerContent = idAnswerContent;
    }

}
