package com.vyatsu.Applicants.models;

import com.vyatsu.Applicants.DB.EnrolleeEntity;
import com.vyatsu.Applicants.DB.ExamEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Exam {
    int score;
    String subject;
    int idEnrollee;
    public Exam(ExamEntity examEntity) {
        this.score= Math.toIntExact(examEntity.getScore());
        this.subject= examEntity.getSubject();
        this.idEnrollee= examEntity.getIdEnrollee();
    }
}
