package com.vyatsu.Applicants.DB;

import com.vyatsu.Applicants.models.Enrollee;
import com.vyatsu.Applicants.models.Exam;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EXAM")
public class ExamEntity {
    @Column(name = "SCORE", nullable = false)
    int score;
    @Id
    @Column(name = "SUBJECT", length = 64, nullable = false)
    String subject;
    @Column(name = "IDENROLLEE", nullable = false)
    int idEnrollee;
    public ExamEntity(Exam exam){
        this.score = exam.getScore();
        this.subject = exam.getSubject();
        this.idEnrollee = exam.getIdEnrollee();
    }
    public int getScore(Exam exam) {
        return this.score = exam.getScore();
    }
    public String getSubject(Exam exam) {
        return this.subject = exam.getSubject();
    }
    public int getIdEnrollee(Exam exam) {
        return this.idEnrollee = exam.getIdEnrollee();
    }
}
