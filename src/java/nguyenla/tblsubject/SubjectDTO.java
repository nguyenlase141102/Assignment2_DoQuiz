/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyenla.tblsubject;

import java.io.Serializable;

/**
 *
 * @author ANH NGUYEN
 */
public class SubjectDTO implements Serializable{
    private String subjectId,subjectName,subjectStatus,timeQuiz;

    public SubjectDTO() {
    }

    public SubjectDTO(String subjectId, String timeQuiz) {
        this.subjectId = subjectId;
        this.timeQuiz = timeQuiz;
    }

    public SubjectDTO(String subjectName) {
        this.subjectName = subjectName;
    }

    public SubjectDTO(String subjectId, String subjectName, String subjectStatus) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.subjectStatus = subjectStatus;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectStatus() {
        return subjectStatus;
    }

    public void setSubjectStatus(String subjectStatus) {
        this.subjectStatus = subjectStatus;
    }
    
}
